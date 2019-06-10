<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class sube extends CI_Controller {
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
		$query=$this->db->query("select * from kullanicilar order by ID");
		$data["veriler"]=$query->result();
		
		$this->load->view('admin/_header');
		$this->load->view('admin/_sidebar');
		$this->load->view('admin/_sube_calisanlari_liste',$data);
		$this->load->view('admin/_footer');
		
		
		
		
	}
	
	public function ekle()
	{
		$this->load->view('admin/_header');
		$this->load->view('admin/_sidebar');
		$this->load->view('admin/_sube_calisan_ekle');
		//$this->load->view('admin/_footer');
	}
	
	public function eklekaydet()
	{
             $data=array (
			
			'adsoy' => $this->input->post('adsoy'),
			'email' => $this->input->post('email'),
			'sifre' => $this->input->post('sifre'),
			'yetki' => $this->input->post('yetki'),
			
			
			);
			
			$this->Database_Model->insert_data("kullanicilar",$data);
	
			$this->session->set_flashdata("sonuc","Ekleme İşlemi Başarı İle Gerçekleştirildi");
			redirect (base_url()."admin/sube");
	}
	
	public function guncellekaydet($id)
	{
		$data=array(
		'adsoy' => $this->input->post('adsoy'),
		'email' => $this->input->post('email'),
		'sifre' => $this->input->post('sifre'),
		'yetki' => $this->input->post('yetki'),
		
		);
		$this->Database_Model->update_data("kullanicilar",$data,$id);
		$this->session->set_flashdata("sonuc","Güncelle İşlemi Başarı İle Gerçekleştirildi");
		
		redirect(base_url()."admin/sube");
	}
	
	function sil($id)
	 {
		 $this->db->query("DELETE FROM kullanicilar WHERE Id=$id");
		 $this->session->set_flashdata("sonuc","Kayıt Silme İşlemi Başarı ile Gerçekleştirildi");
		 redirect (base_url()."admin/kullaniciler");
	 }	 
	 
	public function duzenle($id)
	 {
		$sorgu=$this->db->query("SELECT *  FROM kullanicilar WHERE Id=$id");
		$data["veriler"]=$sorgu->result();
		
		$this->load->view('admin/_header');
		$this->load->view('admin/_sidebar');
		$this->load->view('admin/_sube_calisan_duzenle',$data);
		
		
		 
	 }
	 
	
	
	
	
	
	
}
