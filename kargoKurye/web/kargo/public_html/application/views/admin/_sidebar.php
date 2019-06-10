<!--sidebar start-->
<aside>
    <div id="sidebar" class="nav-collapse">
        <!-- sidebar menu start-->
        <div class="leftside-navigation">
            <ul class="sidebar-menu" id="nav-accordion">
                <li>
                    <a  href="<?=base_url()?>admin/home">
                        <i class="fa fa-dashboard"></i>
                        <span>ANASAYFA</span>
                    </a>
                </li>
				
				
				<?php
				if($this->session->admin_session["yetki"]=="Admin") {?>
				<li class="sub-menu">
                    <a href="<?=base_url()?>admin/sube">
                        <i class="fa fa-group"></i>
                        <span>Çalışanlar</span>
                    </a>
                    
                </li>
				
				<?php
				
				}
				?>
				
			
				
				<li class="sub-menu">
                    <a href="<?=base_url()?>admin/kurye">
                        <i class="fa fa-male"></i>
                        <span>Kuryeler</span>
                    </a>
                    
                </li>
                
                
                
				
				<li class="sub-menu">
                    <a>
                        <i class="fa fa-truck"></i>
                        <span>Kargolar</span>
                    </a>
                    <ul>
                    <li><a href="<?=base_url()?>admin/kargo">Tüm Kargolar</a></li>   
					<li><a href="<?=base_url()?>admin/subede">Şubede</a></li>
					<li><a href="<?=base_url()?>admin/kuryede">Kuryede</a></li>
					<li><a href="<?=base_url()?>admin/teslim">Teslim Edildi</a></li>
					
					
					</ul>
                    
                </li>
                
                <li class="sub-menu">
                    <a href="<?=base_url()?>admin/karg">
                        <i class="fa fa-arrow-circle-left"></i>
                        <span>İstek Kargolar</span>
                    </a>
                    
                </li>
                
                <li class="sub-menu">
                    <a href="<?=base_url()?>admin/kargyon">
                        <i class="fa fa-arrow-circle-right"></i>
                        <span>Kargo Yönlendirme</span>
                    </a>
                    
                </li>
				
				<li class="sub-menu">
                    <a href="<?=base_url()?>admin/musteri">
                        <i class="fa fa-users"></i>
                        <span>Müşteriler</span>
                    </a>
                    
                </li>
                
                <li class="sub-menu">
                    <a href="<?=base_url()?>admin/deneme">
                        <i class="fa fa-male"></i>
                        <span>Deneme</span>
                    </a>
                    
                </li>
				
				
				
				
				
				
                
                
                
				 
             
                
             
				
                
				

                 
				 
               
               
                <li>
                    <a href="<?=base_url()?>admin/login/cikis_yap">
                        <i class="fa fa-sign-out"></i>
                        <span>Çıkış</span>
                    </a>
                </li>
				
				
            </ul>            </div>
        <!-- sidebar menu end-->
    </div>
</aside>
<!--sidebar end-->