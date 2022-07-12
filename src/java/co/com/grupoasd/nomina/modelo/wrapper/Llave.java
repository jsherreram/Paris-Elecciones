/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.grupoasd.nomina.modelo.wrapper;

import java.io.Serializable;

 /**
     * Clase usada para enviar al plugin en el proceso de identificación de Biometría
     * y que relaciona la llave con la huella
     */
   public class Llave implements Serializable{

        private int nroDocumento;
        private int idEvento;
        private int idDivipol;

       public Llave(int nroDocumento, int idEvento, int idDivipol) {
          this.nroDocumento=nroDocumento;
            this.idEvento = idEvento;
            this.idDivipol = idDivipol;
        }

        public int getIdEvento() {
            return idEvento;
        }

        public void setIdEvento(int idEvento) {
            this.idEvento = idEvento;
        }

        public int getIdDivipol() {
            return idDivipol;
        }

        public void setIdDivipol(int idDivipol) {
            this.idDivipol = idDivipol;
        }

    public int getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(int nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    }
