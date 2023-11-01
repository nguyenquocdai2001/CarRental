package com.carrental.demo.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.carrental.demo.model.Account;
import com.carrental.demo.model.Bill;
import com.carrental.demo.model.Car;
import com.carrental.demo.model.Contract;
import com.carrental.demo.model.Driver;
import com.carrental.demo.model.PreOrder;
import com.carrental.demo.repository.AccountRepositoryImpl;
import com.carrental.demo.repository.BillRepositoryImpl;
import com.carrental.demo.repository.CarRepositoryImpl;
import com.carrental.demo.repository.ClientRepositoryImpl;
import com.carrental.demo.repository.ContractRepositoryImpl;
import com.carrental.demo.repository.DriverRepositoryImpl;
import com.carrental.demo.repository.PreOrderRepositoryImpl;
import com.carrental.demo.service.AccountService;
import java.util.Date;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class BillController {
	@Autowired
	private ContractRepositoryImpl contractRepositoryImpl;

	@Autowired
	private BillRepositoryImpl billRepositoryImpl;

	@GetMapping("add-bill/{id}")
	public String addContractrForm(Model model, HttpSession session, @ModelAttribute("bill") Bill bill,
			@PathVariable("id") String idContract) throws ExecutionException, InterruptedException {
		if (session.getAttribute("userSession") != null) {
			Account loggedInUser = (Account) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {

				Contract con = contractRepositoryImpl.getContractById(idContract);

				// Đưa hợp đồng vào model để hiển thị trong biểu mẫu
				model.addAttribute("contract", con);
				model.addAttribute("nameFunction", "Hóa đơn");
				return "./bill/add-bill";

			} else {
				return "redirect:/clientpage";
			}
		}
		return "./login";
	}

	@PostMapping("/add-bill/{id}/add")
	public String addBill(@ModelAttribute("bill") Bill bill) {
		bill.setDate(billRepositoryImpl.formatDate(bill.getDate()));
		billRepositoryImpl.saveBill(bill);
		// Chuyển status của đơn đặt trước thành "đã xong"
		contractRepositoryImpl.editStatusContractById(bill.getId(), "Hết hiệu lực");
		return "redirect:/list-bill";
	}
	
	@GetMapping("edit-bill/{id}")
	public String editBillForm(Model model, HttpSession session, @ModelAttribute("bill") Bill bill, @PathVariable("id") String id)
			throws ExecutionException, InterruptedException {
		if (session.getAttribute("userSession") != null) {
			Account loggedInUser = (Account) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {

				Bill bill1 = billRepositoryImpl.getBillById(id);

				// Đưa hợp đồng vào model để hiển thị trong biểu mẫu
				model.addAttribute("bill", bill1);
				model.addAttribute("nameFunction", "Hóa đơn");
				return "./bill/edit-bill";

			} else {
				return "redirect:/clientpage";
			}
		}
		return "./login";
	}
	
	@PostMapping("/edit-bill/{id}/edit")
	public String editBill(@ModelAttribute("bill") Bill bill,  @RequestParam("file") MultipartFile file, @PathVariable("id") String id) throws IOException, ExecutionException, InterruptedException {
		String nameFile = file.getOriginalFilename();
		if(!(nameFile.isEmpty())) {
			billRepositoryImpl.uploadBillImage(file, id);
			bill.setImage("https://firebasestorage.googleapis.com/v0/b/thyan-b9327.appspot.com/o/bill%2F" + id  + "?alt=media");
		}else
		{
			bill.setImage(bill.getImage());
		}
		bill.setId(id);
		billRepositoryImpl.saveBill(bill);

		return "redirect:/edit-bill/{id}";
	}
	
	@GetMapping("/details-bill/{id}")
	public String showDetailsForm(@PathVariable("id") String id, Model model, HttpSession session) throws ExecutionException, InterruptedException {
		if (session.getAttribute("userSession") != null) {
			Account loggedInUser = (Account) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {
				Bill bill = billRepositoryImpl.getBillById(id);
				
				// Đưa đơn vào model để hiển thị trong biểu mẫu
				model.addAttribute("bill", bill);	
				model.addAttribute("nameFunction", "Hóa đơn");
				return "./bill/detail-bill";
				
			} else {
				return "redirect:/clientpage";
			}
		}
		return "./login";
	}
	
	@RequestMapping(value = "/list-bill", method = RequestMethod.GET)
	public String listBill(Model model, HttpSession session) throws ExecutionException, InterruptedException, ParseException {
		if (session.getAttribute("userSession") != null) {
			Account loggedInUser = (Account) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {
				List<Bill> listBill = new ArrayList<Bill>();
				listBill = billRepositoryImpl.getListBillSortByDate();
				
				String total = billRepositoryImpl.getSumTotalPrice();

				model.addAttribute("listBill", listBill);
				model.addAttribute("totalPrice", total);
				model.addAttribute("nameFunction", "Hóa đơn");
				return "bill/list-bill";
				
			} else {
				return "redirect:/clientpage";
			}
		}
		return "./login";	
	}
	
	@GetMapping("search-bill")
	public String searchBillForm(Model model, HttpSession session, @ModelAttribute("bill") Bill bill) throws ExecutionException, InterruptedException, ParseException {
		if (session.getAttribute("userSession") != null) {
			Account loggedInUser = (Account) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {

//				List<Bill> listBill = billRepositoryImpl.getBillsByYearMonth(idContract);
//
//				// Đưa hợp đồng vào model để hiển thị trong biểu mẫu
//				model.addAttribute("listBill", listBill);
				model.addAttribute("nameFunction", "Hóa đơn");
				return "./bill/search-bill";

			} else {
				return "redirect:/clientpage";
			}
		}
		return "./login";
	}
	
	@PostMapping("search-bill-post")
	public String searchBill(Model model, HttpSession session, @ModelAttribute("bill") Bill bill) throws ExecutionException, InterruptedException, ParseException {
		if (session.getAttribute("userSession") != null) {
			Account loggedInUser = (Account) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {
				//Date dateFormat = billRepositoryImpl.parseDateString(bill.getDate());
				
				List<Bill> listBill = billRepositoryImpl.getBillsByYearMonth(bill.getDate());
				String total = billRepositoryImpl.calculateTotalPaymentByMonthYear(bill.getDate());
				
				// Đưa bill vào model để hiển thị trong biểu mẫu
				model.addAttribute("listBillSearch", listBill);
				model.addAttribute("totalPriceSearch", total);
				model.addAttribute("nameFunction", "Hóa đơn");
				return "./bill/list-bill-search";

			} else {
				return "redirect:/clientpage";
			}
		}
		return "./login";
	}
	
	@RequestMapping(value = "/list-bill-search", method = RequestMethod.GET)
	public String listBillSearch(Model model, HttpSession session) throws ExecutionException, InterruptedException, ParseException {
		if (session.getAttribute("userSession") != null) {
			Account loggedInUser = (Account) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {
				model.addAttribute("nameFunction", "Hóa đơn");
				return "redirect:/bill/list-bill-search";
				
			} else {
				return "redirect:/clientpage";
			}
		}
		return "./login";	
	}
}
