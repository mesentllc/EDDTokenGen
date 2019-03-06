package com.fedex.ground.security.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class BuildKey {
	public static void main(String[] args) throws IOException {
		FxgPasswordCipherSer key = new FxgPasswordCipherSer();
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("P:\\var\\fedex\\hos\\ssm\\fxsp-hos-api.ser")));
		oos.writeObject(key);
		oos.close();
	}
}
