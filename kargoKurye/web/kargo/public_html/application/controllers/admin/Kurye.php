<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class kurye extends CI_Controller {
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
		$query=$this->db->query("select * from kurye order by ID");
		$data["veriler"]=$query->result();
		
		$this->load->view('admin/_header');
		$this->load->view('admin/_sidebar');
		$this->load->view('admin/_kurye_liste',$data);
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
	   
	   $a=0;
	   $b=0;
	   
	   $dataKonum=array(
	       
	       'kurye' => $this->input->post('adsoy'),
			'enlem' => $a,
			'boylam' => $b
	       
	       
	       
	       );
          
			   $data=array (
			
			'username' => $this->input->post('adsoy'),
			'password' => $this->input->post('sifre'),
			'email' => $this->input->post('email'),
			'yetki' => $this->input->post('yetki'),
			'country' => $this->input->post('sehir'),
			
		);
			
			$this->Database_Model->insert_data("kurye",$data);
			$this->Database_Model->insert_data("ortak",$data);
			$this->Database_Model->insert_data("konum",$dataKonum);
	
			$this->session->set_flashdata("sonuc","Ekleme İşlemi Başarı İle Gerçekleştirildi");
			redirect (base_url()."admin/kurye");
	}
	
	public function guncellekaydet($id)
	{
		$data=array(
	    	'username' => $this->input->post('adsoy'),
			'password' => $this->input->post('sifre'),
			'email' => $this->input->post('email'),
			'yetki' => $this->input->post('yetki'),
			'country' => $this->input->post('sehir'),
		
		);
		$this->Database_Model->update_data("kurye",$data,$id);
		$this->Database_Model->update_data("ortak",$data,$id);
		$this->session->set_flashdata("sonuc","Güncelle İşlemi Başarı İle Gerçekleştirildi");
		
		redirect(base_url()."admin/kurye");
	}
	
	function sil($id)
	 {
		 $this->db->query("DELETE FROM kurye WHERE id=$id");
		 $this->session->set_flashdata("sonuc","Kayıt Silme İşlemi Başarı ile Gerçekleştirildi");
		 redirect (base_url()."admin/kurye");
	 }	 
	 
	public function duzenle($id)
	 {
		$sorgu=$this->db->query("SELECT *  FROM kurye WHERE id=$id");
		$data["veriler"]=$sorgu->result();
		
		$this->load->view('admin/_header');
		$this->load->view('admin/_sidebar');
		$this->load->view('admin/_kurye_duzenle',$data);
		
		
		 
	 }
	 
	
	
	
	
	
	
}
