package com.itau.ecom.DTO;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.sun.istack.NotNull;

public class ImagensRequest {

	@Size(min = 1)
	@NotNull
	private List<MultipartFile> imagens = new ArrayList<MultipartFile>();

	public List<MultipartFile> getImagens() {
		return imagens;
	}

	public void setImagens(List<MultipartFile> imagens) {
		this.imagens = imagens;
	}
	
	
	
}
