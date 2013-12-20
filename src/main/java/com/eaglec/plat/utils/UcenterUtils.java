package com.eaglec.plat.utils;

public class UcenterUtils extends PHPFunctions{
	public static String uc_authcode(String $string, String $operation, String $key,int $expiry ) {

		int $ckey_length = 4;	
		//note 随机密钥长度 取值 0-32;
		//note 加入随机密钥，可以令密文无任何规律，即便是原文和密钥完全相同，加密结果也会每次不同，增大破解难度。
		//note 取值越大，密文变动规律越大，密文变化 = 16 的 $ckey_length 次方
		//note 当此值为 0 时，则不产生随机密钥

//		$key = md5( $key!=null ? $key : UC_KEY);
		String $keya = md5(substr($key, 0, 16));
		String $keyb = md5(substr($key, 16, 16));
		String $keyc = $ckey_length > 0? ($operation.equals("DECODE") ? substr($string, 0, $ckey_length): substr(md5(microtime()), -$ckey_length)) : "";

		String $cryptkey = $keya + md5( $keya + $keyc);
		int $key_length = $cryptkey.length();

		$string = $operation.equals("DECODE") ? base64_decode(substr($string, $ckey_length)) : sprintf("%010d", $expiry>0 ? $expiry + time() : 0)+substr(md5($string+$keyb), 0, 16)+$string;
		int $string_length = $string.length();

		StringBuffer $result1 = new StringBuffer();

		int[] $box = new int[256];
		for(int i=0;i<256;i++){
			$box[i] = i;
		}

		int[] $rndkey = new int[256];
		for(int $i = 0; $i <= 255; $i++) {
			$rndkey[$i] = (int)$cryptkey.charAt($i % $key_length);
		}

		int $j=0;
		for(int $i = 0; $i < 256; $i++) {
			$j = ($j + $box[$i] + $rndkey[$i]) % 256;
			int $tmp = $box[$i];
			$box[$i] = $box[$j];
			$box[$j] = $tmp;
		}

		$j=0;
		int $a=0;
		for(int $i = 0; $i < $string_length; $i++) {
			$a = ($a + 1) % 256;
			$j = ($j + $box[$a]) % 256;
			int $tmp = $box[$a];
			$box[$a] = $box[$j];
			$box[$j] = $tmp;
			
			$result1.append((char)( ((int)$string.charAt($i)) ^ ($box[($box[$a] + $box[$j]) % 256])));
			
		}

		if($operation.equals("DECODE")) {
			String $result = $result1.substring(0, $result1.length());
			if((Integer.parseInt(substr($result.toString(), 0, 10)) == 0 || Long.parseLong(substr($result.toString(), 0, 10)) - time() > 0) && substr($result.toString(), 10, 16).equals( substr(md5(substr($result.toString(), 26)+ $keyb), 0, 16))) {
				return substr($result.toString(), 26);
			} else {
				return "";
			}
		} else {
			return $keyc+base64_encode($result1.toString()).replaceAll("=", "");
		}
	}
}
