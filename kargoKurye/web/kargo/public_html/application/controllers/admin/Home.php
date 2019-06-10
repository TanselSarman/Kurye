<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Home extends CI_Controller {
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
		
		$query=$this->db->query("SELECT * FROM kullanicilar");
	    $data["sube_calisan"]=$query->num_rows();
	    
	    $query2=$this->db->query("SELECT * FROM kurye");
	    $data["kuryeler"]=$query2->num_rows();
	    
	    $query3=$this->db->query("SELECT * FROM kargolar");
	    $data["kargolar"]=$query3->num_rows();
	    
	    $query3=$this->db->query("SELECT * FROM ortak WHERE yetki='Musteri'");
	    $data["musteriler"]=$query3->num_rows();

		
		$this->load->view('admin/_header');
		$this->load->view('admin/_sidebar');
		$this->load->view('admin/_content',$data);
		$this->load->view('admin/_footer');
		
	}
	
	public function ayarlar()
	{
		
		
		
		$this->load->view('admin/_header');
		$this->load->view('admin/_sidebar');
		$this->load->view('admin/ayarlar',$data);
		
		
	}
	
	public function login()
	{
		
		$this->load->view('admin/login_form');
		
	}

		public function slayt_yukle($id)
		{
			$query=$this->db->query("SELECT * From slayt WHERE ID= $id");
			$data["veri"]=$query->result();
			
			$data["id"]=$id;
		$this->load->view('admin/_header');
		$this->load->view('admin/_sidebar');
	    $this->load->view('admin\slayt_yukle',$data);
		
		
		}
		
		public function ayarguncelle($id)
	{
		$data=array(
		'seo_baslik' => $this->input->post('adi'),
		'seo_aciklama' => $this->input->post('aciklama'),
		'keywords' => $this->input->post('keywords'),
		'smtpserver' => $this->input->post('smtpserver'),
		'smtpport' => $this->input->post('smtpport'),
		'smtpemail' => $this->input->post('smtpemail'),
		'smtpsifre' => $this->input->post('smtpsifre'),
		'adres' => $this->input->post('adres'),
		'sehir' => $this->input->post('sehir'),
		'tel' => $this->input->post('telefon'),
		'email' => $this->input->post('email'),
		'facebook' => $this->input->post('facebook'),
		'instagram' => $this->input->post('instagram'),
		'hakkimizda' => $this->input->post('hakkimizda'),
		'iletisim' => $this->input->post('iletisim'),
		'twitter' => $this->input->post('twitter'),

		);
		$this->Database_Model->update_data("ayarlar",$data,$id);
		$this->session->set_flashdata("sonuc","Güncelle İşlemi Başarı İle Gerçekleştirildi");
		
		redirect(base_url()."admin/home/ayarlar");
		
		
	}
	
	public function iletisim()
	{
	
		
		
		
		$this->load->view('iletisim',$data);
		
		
		
		
	}

	public function hakkimizda()
	{
		
		
		
		
		
		$this->load->view('hakkimizda',$data);
		
		
		
		
	}
	
}
