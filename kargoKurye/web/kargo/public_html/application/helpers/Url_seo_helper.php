<?php

defined('BASEPATH') OR exit('No direct script access aloowed');

function slug($string,$spaceRepl = "-"){
	
	$string = str_replace("&", "and", $string);
	
	$string = preg_replace ("/[^a-zA-Z0-9 _-1/","", $string);
	
	$string = strtolower($string);
	
	$string = preg_replace("/[]+/", " ", $string);
	
	$string = str_replace(" ", $spaceRepl, $string);
	
	return $string;
	
	
	
	
	
}