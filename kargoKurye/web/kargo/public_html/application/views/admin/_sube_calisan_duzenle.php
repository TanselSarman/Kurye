<!--main content start-->
<section id="main-content">

	<section class="wrapper">
		<!-- //market-->
		<form Id="calisan_ekle" action="<?=base_url()?>/admin/sube/guncellekaydet/<?=$veriler[0]->Id?>" method="post">
		<div class="form-group">
		<table class="table" ui-jq="footable" ui-options='{
        "paging": {
          "enabled": true
        },
        "filtering": {
          "enabled": true
        },
        "sorting": {
          "enabled": true
        }}'>
        <thead>
		
          <tr>
            <th data-breakpoints="xs"><label for="exampleInputEmail1" style="color:#4f4f4f">Ad Soyad</label></th>
            <th><input type="text" value="<?=$veriler[0]->adsoy?>" class="form-control" id="adsoy" name="adsoy" placeholder="Enter email"></th>
            <th></th>
			<th></th>
			
           
            
          </tr>
		   <tr>
            <th data-breakpoints="xs"><label for="exampleInputEmail1" style="color:#4f4f4f">E-Mail</label></th>
            <th><input type="text" value="<?=$veriler[0]->email?>" class="form-control" id="email" name="email" placeholder="Enter email"></th>
            <th></th>
			<th></th>
			
           
            
          </tr>
		   <tr>
            <th data-breakpoints="xs"><label for="exampleInputEmail1" style="color:#4f4f4f">Şifre</label></th>
            <th><input type="text" value="<?=$veriler[0]->sifre?>" class="form-control" id="sifre" name="sifre" placeholder="Enter email"></th>
            <th></th>
			<th></th>
			
           
            
          </tr>
		  
		  <tr>
            <th data-breakpoints="xs"><label for="exampleInputEmail1" style="color:#4f4f4f">Yetki</label></th>
            <th>

                            <select id="exampleInputEmail1" name="yetki" value="<?=$veriler[0]->yetki?>" class="form-control m-bot15">
                                <option>Şube Çalışanı</option>
                                <option>Admin</option>
                                
                            </select></th>
		  </tr>		
		  
		   

		  
            
		  
		  <tr>
		  <th></th><th></th><th></th><th>
		  
		  <button type="submit" data-toggle="modal" style="background-color:#ee30a7" class="btn btn-success pull-right">Güncelle</button>
		 </th></tr>
		   
        </thead>
		
		
                                    
                                    
                                </div>
								
	</form>			

	

          
								
		
			
</section>


								
