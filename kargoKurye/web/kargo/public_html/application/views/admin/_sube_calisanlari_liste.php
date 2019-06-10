<!--main content start-->
<section id="main-content">



	<section class="wrapper">
		<!-- //market-->
		<?php if ($this->session->flashdata("sonuc")) 
		{	  ?>
		<div class="alert alert-danger" role="alert">
					<strong>İşlem!</strong> <?=$this->session->flashdata("sonuc")?>
				</div>
		<?php } ?>
		
		
		<div class="table-agile-info">
 <div class="panel panel-default">
    <div class="panel-heading">
	
     Şube Çalışanları
    </div>
    <div>
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
            <th data-breakpoints="xs">ID</th>
            <th>Adı Soyadı</th>
            <th>E-Mail</th>
			<th>Şifre</th>
			<th>Yetki</th>
			
			
            
           
            
          </tr>
        </thead>
        <tbody>
		
		<?php	
		foreach ($veriler as $rs)
		{
		?>
		
          <tr data-expanded="true">
		  
		  <th scope ="row"><?=$rs->Id?></th>
            <td><?=$rs->adsoy?></td>
            <td><?=$rs->email?></td>
            <td><?=$rs->sifre?></td>
            <td><?=$rs->yetki?></td>
			
			
			<td class="center">
			<td><a href="<?=base_url()?>admin/sube/duzenle/<?=$rs->Id?>" data-toggle="modal" class="btn btn-success">
                                    Düzenle
                                </a></td>
			
						
			
			<td><a href="<?=base_url()?>admin/sube/sil/<?=$rs->Id?>" data-toggle="modal" class="btn btn-danger" onclick="return confirm('Silinecek emin misiniz?!');"  >
                                    Sil
                                </a></td>	
            </td>
			
          </tr>
		  <?php	
		}
		?>
		  
         
        </tbody>
		
      </table>
    </div>
  </div>
</div>
             <a href="<?=base_url()?>admin/sube/ekle" data-toggle="modal" style="background-color:#ee30a7" class="btn btn-success pull-right" >
                                    Çalışan Ekle
                                </a>
								
								
		<!-- //market-->
	
		<div class="agil-info-calendar">
		<!-- calendar -->
		
		<!-- //calendar -->
		
			<div class="clearfix"> </div>
		</div>
			<!-- tasks -->
			
		<!-- //tasks -->
		
</section>
