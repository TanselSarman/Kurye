<!--main content start-->
<section id="main-content">

	<section class="wrapper">
		<!-- //market-->
		<form Id="kullanici_ekle" action="<?=base_url()?>/admin/kurye/eklekaydet" method="post">
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
            <th data-breakpoints="xs"><label for="exampleInputEmail1">Ad Soyad</label></th>
            <th><input type="text" class="form-control" id="adsoy" name="adsoy" placeholder="Enter email"></th>
            <th></th>
			<th></th>
			
           
            
          </tr>
		   <tr>
            <th data-breakpoints="xs"><label for="exampleInputEmail1">E-Mail</label></th>
            <th><input type="text" class="form-control" id="email" name="email" placeholder="Enter email"></th>
            <th></th>
			<th></th>
			
           
            
          </tr>
		   <tr>
            <th data-breakpoints="xs"><label for="exampleInputEmail1">Şifre</label></th>
            <th><input type="text" class="form-control" id="sifre" name="sifre" placeholder="Enter email"></th>
            <th></th>
			<th></th>
			
           
            
          </tr>
		  
		  <tr>
            <th data-breakpoints="xs"><label for="exampleInputEmail1">Yetki</label></th>
            <th>

                            <select id="exampleInputEmail1" name="yetki" class="form-control m-bot15">
                                
                                
                                <option>Kurye</option>
								
                            </select></th>
		  </tr>
		  
		  <tr>
            <th data-breakpoints="xs"><label for="exampleInputEmail1">Şehir</label></th>
            <th>

                            <select id="exampleInputEmail1" name="sehir" class="form-control m-bot15">
                                
                                
                                <option>KARABUK</option>
                                <option>ANKARA</option>
                                <option>ZONGULDAK</option>
                                <option>KASTAMONU</option>
								
                            </select></th>
		  </tr>	
		  
		   

		  
            
		  
		  <tr>
		  <th></th><th></th><th></th><th>
		  
		  <button type="submit" data-toggle="modal" style="background-color:#ee30a7" class="btn btn-success pull-right">Kaydet</button>
		 </th></tr>
		   
        </thead>
		
		
                                    
                                    
                                </div>
								
	</form>			

	

          
								
		
			
</section>


								
