package vn.co.taxinet.bean.driver;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

@ManagedBean(name = "uploadImgController", eager = true)
@SessionScoped
public class UpLoadImgBean {

	private Part file1;
	private String imgExample;
	private String test;
	private String input1;
	private String input2;
	private String input3;
	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public String getInput1() {
		return input1;
	}

	public void setInput1(String input1) {
		this.input1 = input1;
	}

	public String getInput2() {
		return input2;
	}

	public void setInput2(String input2) {
		this.input2 = input2;
	}

	public String getInput3() {
		return input3;
	}

	public void setInput3(String input3) {
		this.input3 = input3;
	}
	
	public void enable(){
		this.test = "1";
	}
	public void disable(){
		this.test = "0";
	}
	public UpLoadImgBean() {
		this.test = "0";
	}
	/*public UpLoadImgBean() {
		this.imgExample = "TN/faces/images/troll.png";
		System.out.print(imgExample);
	}

	public void UpLoadFile() throws IOException{
		InputStream is = this.getFile1().getInputStream();	
        File file = new File( "/TN/faces/images/" + getFileName(getFile1()));
        FileOutputStream os = new FileOutputStream(file);
        byte buffers[] = new byte[2048];
        int byteRead = 0; boolean readMore = true;
        while (readMore){
            byteRead = is.read(buffers);
            if (byteRead>0){
                os.write(buffers);
            }else{
                readMore = false;
            }
        }
        this.imgExample = "TN/faces/images/troll.png" + file.getName();
        os.close(); is.close();
	}
	
	private static String getFileName(Part file){
        String kq = "";
        for (String cd : file.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {  
                String filename = cd.substring(cd.indexOf('=') + 1)
                                    .trim().replace("\"", "");  
                kq = filename.substring(filename.lastIndexOf('/') + 1)
                             .substring(filename.lastIndexOf('\\') + 1);  
                break;
            }  
        }  
        return kq;
    }

	public Part getFile1() {
		return file1;
	}

	public void setFile1(Part file1) {
		this.file1 = file1;
	}

	public String getImgExample() {
		return imgExample;
	}

	public void setImgExample(String imgExample) {
		this.imgExample = imgExample;
	}*/
}
