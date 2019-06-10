<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class musteri extends CI_Controller {
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
		$query=$this->db->query("select * from musteriler order by ID");
		$data["veriler"]=$query->result();
		
		$this->load->view('admin/_header');
		$this->load->view('admin/_sidebar');
		$this->load->view('admin/_müsteri_liste',$data);
		$this->load->view('admin/_footer');
		
		
		
		
	}
	
	public function ekle()
	{
		$this->load->view('admin/_header');
		$this->load->view('admin/_sidebar');
		$this->load->view('admin/_kurye_ekle');
		//$this->load->view('admin/_footer');
	}
	
	public function eklekaydet()
	{
             $data=array (
			
			'username' => $this->input->post('adsoy'),
			'password' => $this->input->post('sifre'),
			'email' => $this->input->post('email'),
			'yetki' => $this->input->post('yetki'),
			'address' => $this->input->post('adres'),
			'phone' => $this->input->post('tel'),
			
			);
			
			$this->Database_Model->insert_data("musteriler",$data);
	
			$this->session->set_flashdata("sonuc","Ekleme İşlemi Başarı İle Gerçekleştirildi");
			redirect (base_url()."admin/musteriler");
	}
	
	public function guncellekaydet($id)
	{
		$data=array(
	    	'username' => $this->input->post('adsoy'),
			'password' => $this->input->post('sifre'),
			'email' => $this->input->post('email'),
			'yetki' => $this->input->post('yetki'),
			'address' => $this->input->post('adres'),
			'phone' => $this->input->post('tel'),
			
		
		
		);
		$this->Database_Model->update_data("musteriler",$data,$id);
		$this->session->set_flashdata("sonuc","Güncelle İşlemi Başarı İle Gerçekleştirildi");
		
		redirect(base_url()."admin/musteri");
	}
	
	function sil($id)
	 {
		 $this->db->query("DELETE FROM musteriler WHERE id=$id");
		 $this->session->set_flashdata("sonuc","Kayıt Silme İşlemi Başarı ile Gerçekleştirildi");
		 redirect (base_url()."admin/musterier");
	 }	 
	 
	public function duzenle($id)
	 {
		$sorgu=$this->db->query("SELECT *  FROM musteriler WHERE id=$id");
		$data["veriler"]=$sorgu->result();
		
		$this->load->view('admin/_header');
		$this->load->view('admin/_sidebar');
		$this->load->view('admin/_musteri_duzenle',$data);
		
		
		 
	 }
	 
	
	
	
	
	
	
}
