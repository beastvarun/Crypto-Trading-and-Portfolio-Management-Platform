package com.project1.model;

import com.project1.domain.verificationType;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Data
public class TwoFactorAuth {
    
    private boolean isenabled = false; // Renamed to 'enabled' for convention
    
    private verificationType sendTo;



	public verificationType getSendTo() {
		return sendTo;
	}

	public void setSendTo(verificationType sendTo) {
		this.sendTo = sendTo;
	}

	public boolean isIsenabled() {
		return isenabled;
	}

	public void setIsenabled(boolean isenabled) {
		this.isenabled = isenabled;
	}

    
}
