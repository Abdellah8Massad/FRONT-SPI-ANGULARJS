package fr.univbrest.dosi.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PROMOTION database table.
 * 
 */
@Entity
@NamedQuery(name="Promotion.findAll", query="SELECT p FROM Promotion p")
public class Promotion implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PromotionPK id;

	private String commentaire;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_RENTREE")
	private Date dateRentree;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_REPONSE_LALP")
	private Date dateReponseLalp;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_REPONSE_LP")
	private Date dateReponseLp;

	@Column(name="LIEU_RENTREE")
	private String lieuRentree;

	@Column(name="NB_MAX_ETUDIANT")
	private BigDecimal nbMaxEtudiant;

	@Column(name="NO_ENSEIGNANT")
	private BigDecimal noEnseignant;

	@Column(name="PROCESSUS_STAGE")
	private String processusStage;

	@Column(name="SIGLE_PROMOTION")
	private String siglePromotion;

	public Promotion() {
	}

	public PromotionPK getId() {
		return this.id;
	}

	public void setId(PromotionPK id) {
		this.id = id;
	}
	
	

	public Promotion(PromotionPK id, String commentaire, Date dateRentree, Date dateReponseLalp, Date dateReponseLp,
			String lieuRentree, BigDecimal nbMaxEtudiant, BigDecimal noEnseignant, String processusStage,
			String siglePromotion) {
		super();
		this.id = id;
		this.commentaire = commentaire;
		this.dateRentree = dateRentree;
		this.dateReponseLalp = dateReponseLalp;
		this.dateReponseLp = dateReponseLp;
		this.lieuRentree = lieuRentree;
		this.nbMaxEtudiant = nbMaxEtudiant;
		this.noEnseignant = noEnseignant;
		this.processusStage = processusStage;
		this.siglePromotion = siglePromotion;
	}

	public String getCommentaire() {
		return this.commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Date getDateRentree() {
		return this.dateRentree;
	}

	public void setDateRentree(Date dateRentree) {
		this.dateRentree = dateRentree;
	}

	public Date getDateReponseLalp() {
		return this.dateReponseLalp;
	}

	public void setDateReponseLalp(Date dateReponseLalp) {
		this.dateReponseLalp = dateReponseLalp;
	}

	public Date getDateReponseLp() {
		return this.dateReponseLp;
	}

	public void setDateReponseLp(Date dateReponseLp) {
		this.dateReponseLp = dateReponseLp;
	}

	public String getLieuRentree() {
		return this.lieuRentree;
	}

	public void setLieuRentree(String lieuRentree) {
		this.lieuRentree = lieuRentree;
	}

	public BigDecimal getNbMaxEtudiant() {
		return this.nbMaxEtudiant;
	}

	public void setNbMaxEtudiant(BigDecimal nbMaxEtudiant) {
		this.nbMaxEtudiant = nbMaxEtudiant;
	}

	public BigDecimal getNoEnseignant() {
		return this.noEnseignant;
	}

	public void setNoEnseignant(BigDecimal noEnseignant) {
		this.noEnseignant = noEnseignant;
	}

	public String getProcessusStage() {
		return this.processusStage;
	}

	public void setProcessusStage(String processusStage) {
		this.processusStage = processusStage;
	}

	public String getSiglePromotion() {
		return this.siglePromotion;
	}

	public void setSiglePromotion(String siglePromotion) {
		this.siglePromotion = siglePromotion;
	}

}