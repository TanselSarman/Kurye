<!--main content start-->
<section id="main-content">

	<section class="wrapper">
		<!-- //market-->
		<form Id="kurye_ekle" action="<?=base_url()?>/admin/kargo/guncellekaydet/<?=$veriler[0]->Id?>" method="post">
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
            <th data-breakpoints="xs"><label for="exampleInputEmail1">Gönderen</label></th>
            <th><input type="text" value="<?=$veriler[0]->gönderen?>"  class="form-control" id="gönderen" name="gönderen" placeholder="Enter email"></th>
            <th></th>
			<th></th>
			
           
            
          </tr>
		   <tr>
            <th data-breakpoints="xs"><label for="exampleInputEmail1">Gönderen Tel</label></th>
            <th><input type="text" value="<?=$veriler[0]->gönderen_tel?>" class="form-control" id="gönderen_tel" name="gönderen_tel" placeholder="Enter email"></th>
            <th></th>
			<th></th>
			
           
            
          </tr>
		  <tr>
            <th data-breakpoints="xs"><label for="exampleInputEmail1">Alıcı</label></th>
            <th><input type="text" value="<?=$veriler[0]->alıcı?>" class="form-control" id="alıcı" name="alıcı" placeholder="Enter email"></th>
            <th></th>
			<th></th>
			
           
            
          </tr>
		  <tr>
            <th data-breakpoints="xs"><label for="exampleInputEmail1">Alıcı Tel</label></th>
            <th><input type="text" value="<?=$veriler[0]->alıcı_tel?>" class="form-control" id="alıcı_tel" name="alıcı_tel" placeholder="Enter email"></th>
            <th></th>
			<th></th>
			
           
            
          </tr>
		  <tr>
            <th data-breakpoints="xs"><label for="exampleInputEmail1">Alıcı Adres</label></th>
            <th>

                            <select id="exampleInputEmail1" name="alıcı_adres" value="<?=$veriler[0]->alıcı_adres?>" class="form-control m-bot15">
                               
                                <option>KARABUK</option>
                                <option>ANKARA</option>
                                <option>ZONGULDAK</option>
                                <option>KASTAMONU</option>
                                
                            </select></th>
		  </tr>	
		  
		  <tr>
            <th data-breakpoints="xs"><label for="exampleInputEmail1">Durum</label></th>
            <th>

                            <select id="exampleInputEmail1" name="durum" value="<?=$veriler[0]->durum?>" class="form-control m-bot15">
                               
                                <option>Subede</option>
                                <option>Kuryede</option>
                                <option>Teslim</option>
                               
                                
                            </select></th>
		  </tr>	
		  
		  <tr>
            <th data-breakpoints="xs"><label for="exampleInputEmail1">Kurye</label></th>
            <th><input type="text" value="<?=$veriler[0]->kurye?>" class="form-control" id="alıcı_tel" name="alıcı_tel" placeholder="Enter email"></th>
            <th></th>
			<th></th>
			
           
            
          </tr>
		  
		  
		  
		   

		  
            
		  
		  <tr>
		  <th></th><th></th><th></th><th>
		  
		  <button type="submit" data-toggle="modal" style="background-color:#ee30a7" class="btn btn-success pull-right">Güncelle</button>
		 </th></tr>
		   
        </thead>
		
		
                                    
                                    
                                </div>
								
	</form>			

	

          
								
		
			
</section>


								
