<?php 

require_once '../includes/DbOperations.php';

$response = array(); 

if($_SERVER['REQUEST_METHOD']=='POST'){
	
		$db = new DbOperations(); 

        $kargo=$db->musteriGonderdigimKargolar($_POST['isim']);
        
        
        for($i=0;$i<count($kargo);$i++)
        {
            $response[$i]['Id']=$kargo[$i][0];
            $response[$i]['gonderen']=$kargo[$i][1];
            $response[$i]['gonderen_tel']=$kargo[$i][2];
            $response[$i]['alici']=$kargo[$i][3];
            $response[$i]['alici_tel']=$kargo[$i][4];
            $response[$i]['alici_adres']=$kargo[$i][5];
            $response[$i]['verilis_tarihi']=$kargo[$i][6];
            $response[$i]['durum']=$kargo[$i][7];
        }
		

	}else{
		$response['error'] = true; 
		$response['message'] = "Required fields are missing";
	
}

echo json_encode($response);