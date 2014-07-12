package to.kit.rpg30;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.lang3.StringUtils;

/**
 * 日記帳関連.(正しくは Nikki だがオリジナルに忠実に...)
 * @author ponta
 */
public final class Niki {
	/** 日記ファイル. */
	private static final File NIKI_FILE = new File("niki");

	/**
	 * 状態をリセット.
	 * @param map マップ情報
	 * @param sts ステータス情報
	 */
	public void reset(MapManager map, Status sts) {
		sts.setAtap(0);
		sts.setAshp(0);
		sts.setAhep(0);
		sts.setAarp(0);
		sts.setExpp(0);
		sts.setMexp(0);
		map.setTgi1(0);
		map.setTgi2(0);
		map.setTgi3(0);
		map.setTgi4(0);
		sts.setGold(0);
		map.setMati(0);

		sts.setVit(0);
		sts.setLev(0);
		sts.setSrn(0);
		sts.setMhp(0);
		sts.setMmp(0);
		sts.setAta(0);
		sts.setAsh(0);
		sts.setAhe(0);
		sts.setAar(0);
		map.setKai(0);
		sts.setMgk(0);
		sts.setSou(0);

		map.setDgx(0);
		map.setDgy(0);
		map.setDsx(0);
		map.setDsy(0);
		map.setTau(0);
		sts.setQmp(0);
		sts.setLev(0);
		sts.setIq(0);
		sts.setHp(0);
		sts.setMp(0);
		map.setGx(0);
		map.setGy(0);

		map.setSx(0);
		map.setSy(0);
		sts.setNa$(StringUtils.EMPTY);
		map.setFe$(StringUtils.EMPTY);
		sts.setKen$(StringUtils.EMPTY);
		//map.setMapf$(StringUtils.EMPTY);
		sts.getItm$()[0] = StringUtils.EMPTY;
		sts.getItm$()[1] = StringUtils.EMPTY;
		map.setDimen(0);
	}

	/**
	 * 状態を保存.
	 * @param map マップ情報
	 * @param sts ステータス情報
	 * @throws IOException 入出力例外
	 */
	public void save(MapManager map, Status sts) throws IOException {
		// 16360  OPEN "SYS\NIKI" FOR OUTPUT AS #1
		// 16365  WRITE #1,ATAP,ASHP,AHEP,AARP,EXPP!,MEXP!,TGI1,TGI2,TGI3,TGI4,GOLD!,MATI
		// 16370  WRITE #1, VIT, LEV, SRN, MHP, MMP, ATA, ASH, AHE, AAR, KAI, MGK, SOU
		// 16375  WRITE #1, DGX, DGY, DSX, DSY, TAU, QMP, LEV,  IQ,  HP,  MP,  GX,  GY
		// 16380  WRITE #1,  SX,  SY, NA$, FE$,KEN$,    MAPF$,  ITM$(0),  ITM$(1),DIMEN
		// 16385  CLOSE
		try (OutputStream fos = new FileOutputStream(NIKI_FILE);
				DataOutputStream out = new DataOutputStream(fos)) {
			out.writeInt(sts.getAtap());
			out.writeInt(sts.getAshp());
			out.writeInt(sts.getAhep());
			out.writeInt(sts.getAarp());
			out.writeInt(sts.getExpp());
			out.writeInt(sts.getMexp());
			out.writeInt(map.getTgi1());
			out.writeInt(map.getTgi2());
			out.writeInt(map.getTgi3());
			out.writeInt(map.getTgi4());
			out.writeInt(sts.getGold());
			out.writeInt(map.getMati());

			out.writeInt(sts.getVit());
			out.writeInt(sts.getLev());
			out.writeInt(sts.getSrn());
			out.writeInt(sts.getMhp());
			out.writeInt(sts.getMmp());
			out.writeInt(sts.getAta());
			out.writeInt(sts.getAsh());
			out.writeInt(sts.getAhe());
			out.writeInt(sts.getAar());
			out.writeInt(map.getKai());
			out.writeInt(sts.getMgk());
			out.writeInt(sts.getSou());

			out.writeInt(map.getDgx());
			out.writeInt(map.getDgy());
			out.writeInt(map.getDsx());
			out.writeInt(map.getDsy());
			out.writeInt(map.getTau());
			out.writeInt(sts.getQmp());
			out.writeInt(sts.getLev());
			out.writeInt(sts.getIq());
			out.writeInt(sts.getHp());
			out.writeInt(sts.getMp());
			out.writeInt(map.getGx());
			out.writeInt(map.getGy());

			out.writeInt(map.getSx());
			out.writeInt(map.getSy());
			out.writeUTF(sts.getNa$());
			out.writeUTF(map.getFe$());
			out.writeUTF(sts.getKen$());
			out.writeUTF(map.getMapf$());
			out.writeUTF(sts.getItm$()[0]);
			out.writeUTF(sts.getItm$()[1]);
			out.writeInt(map.getDimen());
		}
	}

	/**
	 * 状態を復元.
	 * @param map マップ情報
	 * @param sts ステータス情報
	 * @return 復元出来ているっぽい場合はtrue、ファイルが存在しない場合はfalse
	 * @throws IOException 入出力例外
	 */
	public boolean load(MapManager map, Status sts) throws IOException {
		if (!NIKI_FILE.exists()) {
			return false;
		}
		try (InputStream fis = new FileInputStream(NIKI_FILE);
				DataInputStream in = new DataInputStream(fis)) {
			sts.setAtap(in.readInt());
			sts.setAshp(in.readInt());
			sts.setAhep(in.readInt());
			sts.setAarp(in.readInt());
			sts.setExpp(in.readInt());
			sts.setMexp(in.readInt());
			map.setTgi1(in.readInt());
			map.setTgi2(in.readInt());
			map.setTgi3(in.readInt());
			map.setTgi4(in.readInt());
			sts.setGold(in.readInt());
			map.setMati(in.readInt());

			sts.setVit(in.readInt());
			sts.setLev(in.readInt());
			sts.setSrn(in.readInt());
			sts.setMhp(in.readInt());
			sts.setMmp(in.readInt());
			sts.setAta(in.readInt());
			sts.setAsh(in.readInt());
			sts.setAhe(in.readInt());
			sts.setAar(in.readInt());
			map.setKai(in.readInt());
			sts.setMgk(in.readInt());
			sts.setSou(in.readInt());

			map.setDgx(in.readInt());
			map.setDgy(in.readInt());
			map.setDsx(in.readInt());
			map.setDsy(in.readInt());
			map.setTau(in.readInt());
			sts.setQmp(in.readInt());
			sts.setLev(in.readInt());
			sts.setIq(in.readInt());
			sts.setHp(in.readInt());
			sts.setMp(in.readInt());
			map.setGx(in.readInt());
			map.setGy(in.readInt());

			map.setSx(in.readInt());
			map.setSy(in.readInt());
			sts.setNa$(in.readUTF());
			map.setFe$(in.readUTF());
			sts.setKen$(in.readUTF());
			map.setMapf$(in.readUTF());
			sts.getItm$()[0] = in.readUTF();
			sts.getItm$()[1] = in.readUTF();
			map.setDimen(in.readInt());
		}
		return true;
	}
}
