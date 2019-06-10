<?php 

	class DbOperations{

		private $con; 

		function __construct(){

			require_once dirname(__FILE__).'/DbConnect.php';

			$db = new DbConnect();

			$this->con = $db->connect();

		}

		

		public function createUser($username, $pass, $email,$sehr){
			
				//$password = md5($pass);
				/*$stmt = $this->con->prepare("INSERT INTO musteriler (`id`,`username`,`password`,`email`,`yetki`) VALUES (NULL, ?, ?, ?,'Musteri');");
				$stmt->bind_param("sss",$username,$password,$email);*/
				
				
			$stmt2 = $this->con->prepare("INSERT INTO ortak (`id`,`username`,`password`,`email`,`yetki`,`country`) VALUES (NULL, ?, ?, ?,'Musteri',?);");
				$stmt2->bind_param("ssss",$username,$pass,$email,$sehr);
			
		/*$stmt=$mysqli->prepare($sql);
		$stmt->bind_param("ssssss",$username,$pass,$email,$sehr);
		
		$stmt->execute();*/
				

            	if($stmt2->execute()){
					return 1; 
				}else{
					return 2; 
				}
			
		}
		
		public function kuryeKargoKayedet($gonderen, $gonderen_tel, $alici,$alici_tel,$alici_adres,$kurye){
			
				
				
				
			$stmt1 = $this->con->prepare("INSERT INTO siparisler (`Id`,`gonderen`,`gonderen_tel`,`alici`,`alici_tel`,`alici_adres`,`durum`,`kurye`) VALUES (NULL, ?, ?, ?, ?, ?,'yolda',?);");
				$stmt1->bind_param("ssssss",$gonderen,$gonderen_tel,$alici,$alici_tel,$alici_adres,$kurye);
			
		
				

            	if($stmt1->execute()){
					return 1; 
				}else{
					return 2; 
				}
			
		}
		
		public function createMesaj($gonderen, $alici, $mesaj){
			
				
				$stmt = $this->con->prepare("INSERT INTO mesajlar (`Id`,`gonderen`, `alici`, `mesaj`,`deger`) VALUES (NULL, ?, ?, ?,'gorulmedi')");
				$stmt->bind_param("sss",$gonderen,$alici,$mesaj);
				
				

				if($stmt->execute()){
					return 1; 
				}else{
					return 2; 
				}
			
		}
		
		public function kargoGuncelle($gonderen, $alici, $durum){
			
				
				$stmt = $this->con->prepare("UPDATE kargolar SET durum='$durum' WHERE gönderen='$gonderen' AND alıcı='$alici'");
				$stmt->bind_param("sss",$gonderen,$alici,$durum);
				
				

				if($stmt->execute()){
					return 1; 
				}else{
					return 2; 
				}
			
		}
		
		public function mesajDurumGuncelle($gonderen, $alici, $deger){
			
				
				$stmt = $this->con->prepare("UPDATE mesajlar SET deger='$deger' WHERE gonderen='$gonderen' AND alici='$alici'");
				$stmt->bind_param("sss",$gonderen,$alici,$durum);
				
				

				if($stmt->execute()){
					return 1; 
				}else{
					return 2; 
				}
			
		}
		
		

		public function userLogin($username, $pass){
		    //$password = md5($pass);
		/*	$stmt = $this->con->prepare("SELECT id FROM musteriler WHERE username = '$username' AND password = '$password' UNION SELECT id FROM kurye WHERE username = '$username' AND password = '$password'");*/
			
			$stmt = $this->con->prepare("SELECT id FROM ortak WHERE username='$username' AND password='$pass'");
			
			
			$stmt->bind_param("ss",$username,$pass);
			$stmt->execute();
			$stmt->store_result(); 
			return $stmt->num_rows > 0; 
		}

		public function getUserByUsername($username){

	    	$stmt = $this->con->prepare("SELECT * FROM ortak WHERE username = '$username' ");
	        $stmt->bind_param("s",$username);
			$stmt->execute();
			return $stmt->get_result()->fetch_assoc();	
}

        



		private function isUserExist($username, $email){
			$stmt = $this->con->prepare("SELECT id FROM kurye WHERE username = ? OR email = ? UNION SELECT id FROM musteriler WHERE username = ? OR email = ?   ");
			$stmt->bind_param("ss", $username, $email);
			$stmt->execute(); 
			$stmt->store_result(); 
			return $stmt->num_rows > 0; 
		}
		
		public function kargoGetir($sehir){
			$stmt = $this->con->prepare("SELECT * FROM kargolar WHERE alıcı_adres='$sehir' AND durum='Kuryede'");
			$stmt->bind_param("s", $sehir);
			$stmt->execute(); 
			return $stmt->get_result()->fetch_all();
		}
		
		public function musteriGelenKargolar($isim){
			$stmt = $this->con->prepare("SELECT * FROM kargolar WHERE alıcı='$isim' AND durum='Kuryede'");
			$stmt->bind_param("s", $isim);
			$stmt->execute(); 
			return $stmt->get_result()->fetch_all();
		}
		
		public function kuryeGelenIstekler($sehir){
			$stmt = $this->con->prepare("SELECT * FROM kargo_istek WHERE sehir='$sehir' AND durum='onayli'");
			$stmt->bind_param("s", $sehir);
			$stmt->execute(); 
			return $stmt->get_result()->fetch_all();
		}
		
		
		
		public function kargoGetirTeslim($sehir){
			$stmt = $this->con->prepare("SELECT * FROM kargolar WHERE alıcı_adres='$sehir' AND durum='Teslim'");
			$stmt->bind_param("s", $sehir);
			$stmt->execute(); 
			return $stmt->get_result()->fetch_all();
		}
		
		public function musteriGonderdigimKargolar($isim){
			$stmt = $this->con->prepare("SELECT * FROM kargolar WHERE gönderen='$isim'");
			$stmt->bind_param("s", $isim);
			$stmt->execute(); 
			return $stmt->get_result()->fetch_all();
		}
		
		public function musteriAldigimKargolar($isim){
			$stmt = $this->con->prepare("SELECT * FROM kargolar WHERE alıcı='$isim' AND durum='Teslim'");
			$stmt->bind_param("s", $isim);
			$stmt->execute(); 
			return $stmt->get_result()->fetch_all();
		}
		
		public function kuryeGelenMesajListe($alici){
			$stmt = $this->con->prepare("SELECT * FROM mesajlar WHERE alici='$alici'");
			$stmt->bind_param("s", $alici);
			$stmt->execute(); 
			return $stmt->get_result()->fetch_all();
		}
		
		public function kargoGetirAlınacaklar($sehir){
			$stmt = $this->con->prepare("SELECT * FROM kargolar WHERE alıcı_adres='$sehir' AND durum='Subede'");
			$stmt->bind_param("s", $sehir);
			$stmt->execute(); 
			return $stmt->get_result()->fetch_all();
		}
		
		public function kuryeMesajGetir($gonderen,$alici){
			
			$stmt = $this->con->prepare("SELECT * FROM mesajlar WHERE gonderen='$gonderen' AND alici='$alici'");
			$stmt->bind_param("ss",$gonderen,$alici);
			$stmt->execute();
			
			return $stmt->get_result()->fetch_assoc();
		}
		
		public function musteriKuryeMesajGetir($alici){
			
			$stmt = $this->con->prepare("SELECT * FROM mesajlar WHERE alici='$alici'");
			$stmt->bind_param("s",$alici);
			$stmt->execute();
			
			return $stmt->get_result()->fetch_assoc();
		}
		
		public function musteriMusteriMesajGetir($gonderen,$alici){
			
			$stmt = $this->con->prepare("SELECT * FROM mesajlar WHERE gonderen='$gonderen' AND alici='$alici'");
			$stmt->bind_param("ss",$gonderen,$alici);
			$stmt->execute();
			
			return $stmt->get_result()->fetch_assoc();
		}
		
		
		public function konumGuncelle($kurye, $enlem, $boylam){
			
				
				$stmt = $this->con->prepare("UPDATE konum SET kurye='$kurye', enlem='$enlem', boylam='$boylam' WHERE kurye='$kurye' ");
				$stmt->bind_param("sss",$kurye,$enlem,$boylam);
				
				

				if($stmt->execute()){
					return 1; 
				}else{
					return 2; 
				}
			
		}
		
		public function konumKaydet($kurye, $enlem, $boylam){
			
				
				$stmt = $this->con->prepare("INSERT INTO konum (`Id`,`kurye`, `enlem`, `boylam`) VALUES (NULL, ?, ?, ?)");
				$stmt->bind_param("sss",$kurye,$enlem,$boylam);
				
				

				if($stmt->execute()){
					return 1; 
				}else{
					return 2; 
				}
			
		}
		
		public function kargoIstek($kullanici, $enlem, $boylam, $sehir){
			
				
				$stmt = $this->con->prepare("INSERT INTO kargo_istek (`Id`,`kullanici`, `enlem`, `boylam`, `sehir`, `durum`) VALUES (NULL, ?, ?, ?, ?, 'beklemede')");
				$stmt->bind_param("ssss",$kullanici,$enlem,$boylam,$sehir);
				
				
				if($stmt->execute()){
					return 1; 
				}else{
					return 2; 
				}
			
		}
		
		public function konumAl($kurye){
			
			$stmt = $this->con->prepare("SELECT * FROM konum WHERE kurye='$kurye'");
			$stmt->bind_param("s",$kurye);
			$stmt->execute();
			
			return $stmt->get_result()->fetch_assoc();
		}
		
		public function konumKontrol($kurye){
			
			$stmt = $this->con->prepare("SELECT * FROM konum WHERE kurye='$kurye'");
			$stmt->bind_param("s",$kurye);
			$stmt->execute();
			
			return $stmt->get_result()->fetch_assoc();
		}
		
		public function sehirAl(){
			
			$stmt = $this->con->prepare("SELECT * FROM sehirler");
			
			$stmt->execute();
			
			return $stmt->get_result()->fetch_all();
		}
		
		public function createMesajMusteri($gonderen, $alici, $mesaj){
			
				
				$stmt = $this->con->prepare("INSERT INTO mesajlar (`Id`,`gonderen`, `alici`, `mesaj`,`deger`) VALUES (NULL, ?, ?, ?,'gorulmedi')");
				$stmt->bind_param("sss",$gonderen,$alici,$mesaj);
				
			/*	$stmt2=$this->con->prepare("UPDATE mesajlar SET deger='goruldu' WHERE gonderen='$alici' AND alici='$gonderen'");
			    $stmt2->bind_param();*/
				

				if($stmt->execute()){
					return 1; 
				}else{
					return 2; 
				}
			
		}
		
		public function kuryeIstekSil($id){
			
				
				
				
			$stmt1 = $this->con->prepare("DELETE FROM kargo_istek WHERE Id='$id'");
		    $stmt1->bind_param("s",$id);
			
		
				

            	if($stmt1->execute()){
					return 1; 
				}else{
					return 2; 
				}
			
		}

	}