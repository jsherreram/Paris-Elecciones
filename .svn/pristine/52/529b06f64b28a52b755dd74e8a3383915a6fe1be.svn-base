package co.com.grupoasd.nomina.common.model;

import java.io.Serializable;

public class Acceso implements Serializable {

	private static final long serialVersionUID = -7151154316299915883L;
	private boolean permitido;
	private String identityId;
	private String issued;
    private String expires;
	
	/**
	 * Permite crear un objeto con permitido en false e identidad vacia.
	 * @author Juan Carlos Castellanos 
	 */
	public Acceso() {
		this.permitido = false;
		this.identityId = "";
	}
	/**
	 * Permite crear un objeto con la identidad especificada y con permitido en true
	 * @author Juan Carlos Castellanos
	 * Constructor utilitar
	 * @param identityId Identidad
	 */
	public Acceso(String identityId) {
	    super();
	    this.permitido = true;
	    this.identityId = identityId;
    }

	public boolean isPermitido() {
		return permitido;
	}

	public void setPermitido(boolean valor) {
		this.permitido = valor;
	}

	public String getIdentityId() {
		return identityId;
	}

	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}
	
	
	
	public String getIssued() {
		return issued;
	}
	public void setIssued(String issued) {
		this.issued = issued;
	}
	public String getExpires() {
		return expires;
	}
	public void setExpires(String expires) {
		this.expires = expires;
	}
	@Override
    public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result
	            + ((identityId == null) ? 0 : identityId.hashCode());
	    return result;
    }
	@Override
    public boolean equals(Object obj) {
	    if (this == obj)
		    return true;
	    if (obj == null)
		    return false;
	    if (getClass() != obj.getClass())
		    return false;
	    Acceso other = (Acceso) obj;
	    if (identityId == null) {
		    if (other.identityId != null)
			    return false;
	    } else if (!identityId.equals(other.identityId))
		    return false;
	    return true;
    }
	@Override
    public String toString() {
	    return "Acceso [permitido=" + permitido + ", identityId=" + identityId + "]";
    }
	
	
	
	
	
	
}
