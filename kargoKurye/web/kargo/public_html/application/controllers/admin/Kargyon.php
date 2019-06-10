<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class kargyon extends CI_Controller {
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
		$query=$this->db->query("select * from siparisler order by ID");
		$data["veriler"]=$query->result();
		
		$this->load->view('admin/_header');
		$this->load->view('admin/_sidebar');
		$this->load->view('admin/_kargo_yon_liste',$data);
		$this->load->view('admin/_footer');
		
		
		
		
	}
	
	public function eklekaydet()
	{
	    
	    
	    
	    
	    
	    $sehr=$this->input->post('alıcı_adres');
	    
	    /*$sorgu=$this->db->query("SELECT * FROM kurye WHERE country=$sehr");
	    $res = $sorgu->result(); 
        $row = $res[1]; */
        
        $row = $this -> db
       -> select('username')
       -> where('country', $sehr)
       -> limit(1)
       -> get('kurye')
       -> row()
        ->username;
	    
	   /* $this->db->select('username');
			$this->db->from('kurye');
			$this->db->where('country',$sehr);
			
			
			$query=$this->db->get();*/


			//$ret=$query->result();
	    
             $data=array (
			
			'gönderen' => $this->input->post('gönderen'),
			'gönderen_tel' => $this->input->post('gönderen_tel'),
			'alıcı' => $this->input->post('alıcı'),
			'alıcı_tel' => $this->input->post('alıcı_tel'),
			'alıcı_adres' => $this->input->post('alıcı_adres'),
			'durum' => $this->input->post('durum'),
			'kurye' => $row
			
			);
			
			$this->Database_Model->insert_data("kargolar",$data);
			
			$id=$this->input->post('id');
	        $this->db->query("DELETE FROM siparisler WHERE Id=$id");
	
			$this->session->set_flashdata("sonuc","Yönlendirme İşlemi Başarı İle Gerçekleştirildi");
			redirect (base_url()."admin/kargyon");
	}
	
	
	
	function sil($id)
	 {
		 $this->db->query("DELETE FROM siparisler WHERE Id=$id");
		 $this->session->set_flashdata("sonuc","Kayıt Silme İşlemi Başarı ile Gerçekleştirildi");
		 redirect (base_url()."admin/kargyon");
	 }	 
	 
	 public function duzenle($id)
	 {
		$sorgu=$this->db->query("SELECT *  FROM siparisler WHERE Id=$id");
		$data["veriler"]=$sorgu->result();
		
		$this->load->view('admin/_header');
		$this->load->view('admin/_sidebar');
		$this->load->view('admin/_kargo_yon_duzenle',$data);
		
		
		 
	 }
	 

}
