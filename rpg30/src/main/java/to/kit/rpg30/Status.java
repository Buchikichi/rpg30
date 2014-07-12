package to.kit.rpg30;

import org.apache.commons.lang3.StringUtils;

/**
 * 主人公のステータス.
 * @author ponta
 */
public final class Status {
//	16365  WRITE #1,ATAP,ASHP,AHEP,AARP,EXPP!,MEXP!,TGI1,TGI2,TGI3,TGI4,GOLD!,MATI
//	16370  WRITE #1, VIT, LEV, SRN, MHP, MMP, ATA, ASH, AHE, AAR, KAI, MGK, SOU
//	16375  WRITE #1, DGX, DGY, DSX, DSY, TAU, QMP, LEV,  IQ,  HP,  MP,  GX,  GY
//	16380  WRITE #1,  SX,  SY, NA$, FE$,KEN$,    MAPF$,  ITM$(0),  ITM$(1),DIMEN
	private int atap;
	private int ashp;
	private int ahep;
	private int aarp;
	private int expp;
	private int mexp;
	private int gold;
	private int vit;
	private int qmp;
	private int lev;
	private int srn;
	private int mhp;
	private int mmp;
	private int ata;
	private int ash;
	private int ahe;
	private int aar;
	private int mgk;
	private int sou;
	private int iq;
	private int hp;
	private int mp;
	private String na$ = StringUtils.EMPTY;
	private String fe$ = StringUtils.EMPTY;
	private String ken$ = StringUtils.EMPTY;
	private String[] itm$ = { StringUtils.EMPTY, StringUtils.EMPTY };
	private boolean gameover;

	public int getAtap() {
		return this.atap;
	}
	public void setAtap(int val) {
		this.atap = val;
	}
	public int getAshp() {
		return this.ashp;
	}
	public void setAshp(int val) {
		this.ashp = val;
	}
	public int getAhep() {
		return this.ahep;
	}
	public void setAhep(int val) {
		this.ahep = val;
	}
	public int getAarp() {
		return this.aarp;
	}
	public void setAarp(int val) {
		this.aarp = val;
	}
	public int getExpp() {
		return this.expp;
	}
	public void setExpp(int val) {
		this.expp = val;
	}
	public void addExpp(int val) {
		this.expp += val;
	}
	public int getMexp() {
		return this.mexp;
	}
	public void setMexp(int val) {
		this.mexp = val;
	}
	public int getGold() {
		return this.gold;
	}
	public void setGold(int val) {
		this.gold = val;
	}
	public void addGold(int val) {
		this.gold += val;
	}
	public int getVit() {
		return this.vit;
	}
	public void setVit(int val) {
		this.vit = val;
	}
	public int getQmp() {
		return this.qmp;
	}
	public void setQmp(int val) {
		this.qmp = val;
	}
	public int getLev() {
		return this.lev;
	}
	public void setLev(int val) {
		this.lev = val;
	}
	public int getSrn() {
		return this.srn;
	}
	public void setSrn(int val) {
		this.srn = val;
	}
	public int getMhp() {
		return this.mhp;
	}
	public void setMhp(int val) {
		this.mhp = val;
	}
	public int getMmp() {
		return this.mmp;
	}
	public void setMmp(int val) {
		this.mmp = val;
	}
	public int getAta() {
		return this.ata;
	}
	public void setAta(int val) {
		this.ata = val;
	}
	public int getAsh() {
		return this.ash;
	}
	public void setAsh(int val) {
		this.ash = val;
	}
	public int getAhe() {
		return this.ahe;
	}
	public void setAhe(int val) {
		this.ahe = val;
	}
	public int getAar() {
		return this.aar;
	}
	public void setAar(int val) {
		this.aar = val;
	}
	public int getMgk() {
		return this.mgk;
	}
	public void setMgk(int val) {
		this.mgk = val;
	}
	public int getSou() {
		return this.sou;
	}
	public void setSou(int val) {
		this.sou = val;
	}
	public int getIq() {
		return this.iq;
	}
	public void setIq(int val) {
		this.iq = val;
	}
	public int getHp() {
		return this.hp;
	}
	public void setHp(int val) {
		this.hp = val;
	}
	public void addHp(int val) {
		this.hp += val;
	}
	public int getMp() {
		return this.mp;
	}
	public void setMp(int val) {
		this.mp = val;
	}
	public void addMp(int val) {
		this.mp += val;
	}
	public String getNa$() {
		return this.na$;
	}
	public void setNa$(String val) {
		this.na$ = val;
	}
	public String getFe$() {
		return this.fe$;
	}
	public void setFe$(String val) {
		this.fe$ = val;
	}
	public String getKen$() {
		return this.ken$;
	}
	public void setKen$(String val) {
		this.ken$ = val;
	}
	public String[] getItm$() {
		return this.itm$;
	}
	public boolean isGameover() {
		return this.gameover;
	}
	public void setGameover(boolean b) {
		this.gameover = b;
	}
}
