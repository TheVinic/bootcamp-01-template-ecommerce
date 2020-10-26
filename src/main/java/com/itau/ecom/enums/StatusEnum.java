package com.itau.ecom.enums;

public enum StatusEnum {

	INICIADA(1, "iniciada");
	
	private Integer id;
	private String status;
	
	StatusEnum(Integer id, String status) {
		this.id = id;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}
	
	/*public Status retornaStatus(Integer idStatus) {
		
		for(StatusEnum e : StatusEnum.values()) {
			if(idStatus.equals(e.getId())) {
				return new Status(e.getId(), e.getStatus());
			}
		}
		return null;
		
	}*/
	
}
