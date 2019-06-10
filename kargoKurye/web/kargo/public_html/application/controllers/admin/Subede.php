<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class subede extends CI_Controller {
      public function __construct()
	  {
		  parent::__construct();
		  
		  
		  $this->load->helper('url');
		  $this->load->library('session');
		  $this->load->database();
		  $this->load->model('Database_Model');
		  
		 if(!$this->session->userdata("admin_session"))
		  {
			  
			  $this->session->set_flashdata("login_hata","Önce Giriş Yapınız");
			  redirect(base_url().'admin/login');  
		  }	
		  
	  }
	public function index()
	{
		$query=$this->db->query("select * from kargolar WHERE durum='Subede' order by ID");
		$data["veriler"]=$query->result();
		
		$this->load->view('admin/_header');
		$this->load->view('admin/_sidebar');
		$this->load->view('admin/_kargo_liste',$data);
		$this->load->view('admin/_footer');
		
		
		
		
	}
	
	
	
	
	
	
	 
	
	
	
	
	
	
}
