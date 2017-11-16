package com.lol.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

//import test.java.com.lol.tests.Task;
import com.lol.util.TestExecutor;

public class LOL_OrderManagement_Creation_RSO extends TestExecutor {

	public String lol_menu = "table[title=\"Task Type:Task View, Fastpath Code: TV:80\"]";
	public String win_field_solution = "table[title=\"Task Type: Folder, Fastpath Code: JDE032649\"] > tbody > tr > td";
	public String win_field_operation = "table[title=\"Task Type: Folder, Fastpath Code: JDE032655\"] > tbody > tr > td";
	public String win_field_marketing_operation = "table[title=\"Task Type: Folder, Fastpath Code: JDE032730\"] > tbody > tr > td";
	public String win_field_sales_order_holds = "table[title=\"Task Type: Folder, Fastpath Code: JDE031782\"] > tbody > tr > td";
	public String win_field_manual_hold_order_release = "table[title=\"Application: P5543070, Form: W5543070A, Version: UCS007\"] > tbody > tr > td";
	public String manual_order_number = ".//*[@id='C0_36']";

	public String win_field_ware_house_user = "table[title=\"Task Type: Folder, Fastpath Code: JDE032672\"] > tbody > tr > td";
	public String win_field_order_management = "table[title=\"Task Type: Folder, Fastpath Code: JDE032651\"] > tbody > tr > td";
	public String win_field_ware_inventryu = "table[title=\"Task Type: Folder, Fastpath Code: JDE032657\"] > tbody > tr > td";
	public String win_field_ware_order_hard_commit = "table[title=\"Application: P5542101, Form: W5542101C, Version: UCS030\"] > tbody > tr > td";

	public String win_field_pick_manual = "table[title=\"Task Type: Folder, Fastpath Code: JDE032667\"] > tbody > tr > td";
	public String win_field_pick_manual_hard_commit = "table[title=\"Report: R5542520, Version: UPM002\"] > tbody > tr > td";

	public String win_field_order_number = ".//*[@id='C0_366']";
	public String win_field_The_order_number_in_grid = ".//*[@id='G0_41_R0']/td[1]/div/input";
	public String win_field_edit_full_header = ".//*[@id='C0_214_207']";
	public String win_field_hold_code = ".//*[@id='C0_21_41']";
	public String win_field_orde_number = ".//*[@id='C0_21_20']";
	public String win_field_save_and_close = ".//*[@id='C0_19']";
	public String win_field_submit_and_close = ".//*[@id='C0_136']";
	public String win_field_stop_code = ".//*[@id='G0_1_R0']/td[3]/div/input";

	public String win_field_house_user = "table[title=\"Task Type: Folder, Fastpath Code: JDE032672\"] > tbody > tr > td";
	public String winf_manaufacturing = "table[title=\"Task Type: Folder, Fastpath Code: JDE031811\"] > tbody > tr > td";
	public String winf_asc = "table[title=\"Task Type: Folder, Fastpath Code: JDE031830\"] > tbody > tr > td";
	// Application: P48013, Form: W48013J, Version: UPM001
	public String Work_Order_Entry = "table[title=\"Application: P48013, Form: W48013J, Version: UPM001\"] > tbody > tr > td";

	public String win_field_picking_and_chipping = "table[title=\"Task Type: Folder, Fastpath Code: JDE032666\"] > tbody > tr > td";
	public String winf_inbound_inventry = "table[title=\"Task Type: Folder, Fastpath Code: JDE032684\"] > tbody > tr > td";
	public String winf_enter_transfer_ot = "table[title=\"Application: P5643250, Form: W5643250A, Version: UPM001\"] > tbody > tr > td";

	// Task Type: Folder, Fastpath Code: JDE032684

	public String winF_pick_manual_ticket = "table[title=\"Task Type: Folder, Fastpath Code: JDE032667\"] > tbody > tr > td";

	public String print_pick_ticket_hard_commit = "table[title=\"Report: R5542520, Version: UPM002\"] > tbody > tr > td";

	// Report: R5542520, Version: UPM002

	// Task Type: Folder, Fastpath Code: JDE032667

	public String win_field_work_with_shipment = "table[title=\"Task Type: Folder, Fastpath Code: JDE032179\"] > tbody > tr > td";
	public String win_field_work_with_shipment_confirmation = "table[title=\"Task Type: Folder, Fastpath Code: JDE031791\"] > tbody > tr > td";
	public String win_field_work_with_ship_confirm = "table[title=\"Application: P4205, Form: W4205H, Version: UPM002\"] > tbody > tr > td";
	
	
	
	
	
	
	public String win_field_fleet_shipping_processing = "table[title=\"Application: P5542080, Form: W5542080A, Version: UCS001\"] > tbody > tr > td";

	public String win_field_picking_confirmation = "table[title=\"Task Type: Folder, Fastpath Code: JDE031789\"] > tbody > tr > td";
	public String win_field_pick_confirm_ASC = "table[title=\"Application: P4205, Form: W4205H, Version: UPM001\"] > tbody > tr > td";
	public String win_field_sales_order_line = ".//*[@id='G0_1_R0']/td[1]/div/input";
	public String confirm_shows_rows = ".//*[@id='HE0_80']/tbody/tr/td[2]/span/nobr";

	public String order_management = "table[title=\"Task Type: Folder, Fastpath Code: JDE031019\"] > tbody > tr > td";
	public String sales_order_processing = "table[title=\"Task Type: Folder, Fastpath Code: JDE031267\"] > tbody > tr > td";
	public String sales_order = "table[title=\"Task Type: Folder, Fastpath Code: JDE031044\"] > tbody > tr > td";
	public String enter_sales_order = "table[title=\"Application: P5542101, Form: W5542101C, Version: UCS001\"] > tbody > tr > td";
	public String counter_sales_order_management = "table[title=\"Task Type: Folder, Fastpath Code: JDE030824\"] > tbody > tr > td";
	public String counter_sales_enter_order = "table[title=\"Application: P5542101, Form: W5542101C, Version: UCS050\"] > tbody > tr > td";
	public String lol_actions = "table[title=\"Actions\"] > tbody > tr > td";
	public String view_job_status = "table[title=\"Application: P986116, Form: W986116A, Version: ZJDE0001\"] > tbody > tr > td";
	public String customer_so_entry_no_hard_pickup = "table[title=\"Application: P5542101, Form: W5542101C, Version: UCS001\"] > tbody > tr > td";
	public String shated_services_unity_menus = "table[title=\"Task Type: Folder, Fastpath Code: JDE030649\"] > tbody > tr > td";
	public String account_receivable = "table[title=\"Task Type: Folder, Fastpath Code: JDE030660\"] > tbody > tr > td";
	public String ar_cash_application = "table[title=\"Task Type: Folder, Fastpath Code: JDE030858\"] > tbody > tr > td";
	public String customer_ledge_inquiry = "table[title=\"Application: P03B2002, Form: W03B2002A, Version: UFN001\"] > tbody > tr > td";
	public String ar_credit_collection = "table[title=\"Task Type: Folder, Fastpath Code: JDE030870\"] > tbody > tr > td";
	public String ar_credit_granting_management = "table[title=\"Application: P03B305, Form: W03B305C\"] > tbody > tr > td";
	public String ware_house_managemenet = "table[title=\"Task Type: Folder, Fastpath Code: JDE031806\"] > tbody > tr > td";
	public String inventry_inquires = "table[title=\"Task Type: Folder, Fastpath Code: JDE031919\"] > tbody > tr > td";
	public String item_availability = "table[title=\"Application: P5641202, Form: W5641202A\"] > tbody > tr > td";

	public String win_field_solution_supply = "table[title=\"Task Type: Folder, Fastpath Code: JDE032653\"] > tbody > tr > td";

	public String win_field_soultion_supply_reports = "table[title=\"Task Type: Folder, Fastpath Code: JDE032808\"] > tbody > tr > td";
	public String win_field_solution_po_compliance = "table[title=\"Report: R5543001, Version: UPM001\"] > tbody > tr > td";

	// Work with Pick Confirmation [age elements
	public String pick_confirm_page_order_number = ".//*[@id='C0_19']";

	public String supply = "table[title=\"Task Type: Folder, Fastpath Code: JDE032653\"] > tbody > tr > td";
	public String procurement_supply = "table[title=\"Task Type: Folder, Fastpath Code: JDE031807\"] > tbody > tr > td";
	public String chemical_cpp = "table[title=\"Task Type: Folder, Fastpath Code: JDE031866\"] > tbody > tr > td";
	public String purchase_order_entry_dc = "table[title=\"Application: P4310, Form: W4310G, Version: UPM017\"] > tbody > tr > td";
	public String detail_revision = ".//*[@id='HE0_176']/tbody/tr/td[2]/span/nobr";
	public String regulatory_affairs = "table[title=\"Task Type: Folder, Fastpath Code: JDE030664\"] > tbody > tr > td";
	public String regulatory_code_maintainence = "table[title=\"Application: P5542061, Form: W5542061A\"] > tbody > tr > td";
	public String sample_regulatory_code = ".//*[@id='qbeRow0_1']/td[2]/div/nobr/input";
	public String item_number_for_item_availability = ".//*[@id='C0_17']";
	public String item_allocated_not_returnable_error_message = "//a[contains(text(),'Item is ALLOCATED,  NONRETURNABLE')]";
	public String approval_menu = "table[title=\"Task Type: Folder, Fastpath Code: JDE031868\"] > tbody > tr > td";
	public String direct_ship_management = "table[title=\"Application: P5543025, Form: W5543025A, Version: UPM001\"] > tbody > tr > td";

	public String random_invoice = ".//*[@id='G0_1_R1']/td[1]/div/input";
	public String create_tab = "CT0_23.1";
	public String create_limit = "C0_63";
	public String create_manager = ".//*[@id='C0_480']";
	public String regulatory_order_field = ".//*[@id='G0_1_R0']/td[2]/div";

	public String procurement = "table[title=\"Task Type: Folder, Fastpath Code: JDE031807\"] > tbody > tr > td";
	public String inquries_report = "table[title=\"Task Type: Folder, Fastpath Code: JDE031877\"] > tbody > tr > td";
	public String public_order_enquiry = "table[title=\"Application: P4310, Form: W4310G, Version: UPM015\"] > tbody > tr > td";

	public String order_management_inquires = "table[title=\"Task Type: Folder, Fastpath Code: JDE031091\"] > tbody > tr > td";
	public String sales_order_inquires = "table[title=\"Application: P5542101, Form: W5542101C, Version: UCS09\"] > tbody > tr > td";

	public String pre_pay_contract_management = "table[title=\"Task Type: Folder, Fastpath Code: JDE030676\"] > tbody > tr > td";
	public String pre_pay_contractreports = "table[title=\"Task Type: Folder, Fastpath Code: JDE031446\"] > tbody > tr > td";
	public String contract_prepay_open_reports = "table[title=\"Report: R5542369\"] > tbody > tr > td";

	public String product_costing = "table[title=\"Task Type: Folder, Fastpath Code: JDE031096\"] > tbody > tr > td";
	public String month_end_casting_task = "table[title=\"Task Type: Folder, Fastpath Code: JDE031183\"] > tbody > tr > td";
	public String National_average_calculation_07_costed_item = "table[title=\"Report: R5541056, Version: UFN001\"] > tbody > tr > td";
	// Tool Bar
	public String select_ok_from_tool_bar = ".//*[@id='hc_Select']";
	public String checkboxfrom_tool_bar = ".//*[@id='C0_23']";
	public String select_submit_from_tool_bar = ".//*[@id='divC0_30']/span";
	public String form_exit_button = ".//*[@id='FORM_EXIT_BUTTON']";
	public String submitted_jobs = ".//*[@id='HE0_121']/tbody/tr/td[2]/span/nobr";
	public String row_exit_button = ".//*[@id='ROW_EXIT_BUTTON']";
	public String seelct_dot_reange = ".//*[@id='HE0_33']/tbody/tr/td[2]/span/nobr";
	public String epa_information = ".//*[@id='HE0_30']/tbody/tr/td[2]/span/nobr";
	public String registration = ".//*[@id='HE0_31']/tbody/tr/td[2]/span/nobr";
	public String item_number = "//*[@id='G0_1_R3']/td[4]/div/input";
	public String quantity = ".//*[@id='G0_1_R3']/td[5]/div/input";
	public String next_row = ".//*[@id='G0_1_R2']/td[4]/div";
	public String chemical_activity_log = ".//*[@id='HE0_32']/tbody/tr/td[2]/span/nobr";

	// Processing Options
	public String as_of_date = ".//*[@id='PO1T0']";
	public String display_ar_detail = ".//*[@id='PO2T0']";
	public String sales_order_detail = ".//*[@id='PO3T0']";
	public String business_selection = ".//*[@id='PO4T0']";
	public String processing_option_cancel = ".//*[@id='hc2']";

	// Finance
	public String order_number = "C0_13";
	public String line_type_first = ".//*[@id='G0_1_R0']/td[1]/div/input";
	public String pro_internal_order = ".//*[@id='HE0_384']/tbody/tr/td[2]/span/nobr";
	public String shipment_tracking = ".//*[@id='HE0_34']/tbody/tr/td[2]/span/nobr";
	public String track_shipment = ".//*[@id='HE0_27']/tbody/tr/td[2]/span/nobr";

	// Order Header Revision Page

	public String MB_LeftSideLinksAll = ".//*[contains(@id, 'pt1:i1:') and contains(@id, ':cil3')]";

	public int linkToCheck;

}