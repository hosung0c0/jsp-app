package common;

public class Paging {
	
	// ����¡ DTO ����
	PagingDTO pgdto = new PagingDTO();
	// DB���� Ŭ���� �����ϱ�
	JDBConnector jdbc = new JDBConnector();

	///////////////////////
	// ������ �޼��� /////////
	///////////////////////
	// ����: �ν��Ͻ� ������ �ٷ� �����ϹǷ� �⺻ �������� ��� �����Ѵ�!
	public Paging() {

		/********************************* 
		15. ����¡ ��ũ �����ϱ�
		______________________________

		1)���� ���ڵ��ȣ : startNum
		2)�������� ���ڵ尳�� : onePageCnt
		3)��ü ���ڵ�� : totalCnt
		4)����Ʈ �׷�� : listGroup
			(��ü���� �� �������簳��) 
		5)���� ���ڵ�� : etcRecord
		6)����¡��ũ �ڵ� ���庯�� pgCode

		 *********************************/
		try {
			// 15-1. ��ü ���ڵ� �� ���ϱ�
			// ���ڵ�� ���ϱ� ����
			String cntQuery = "SELECT COUNT(*) FROM `drama_info`";
			// ������ PreparedStatement�� �ֱ�
			jdbc.pstmt = jdbc.conn.prepareStatement(cntQuery);
			// ��������! -> ���������� ���Ϲ޾� ResultSet�� ��´�!
			jdbc.rs = jdbc.pstmt.executeQuery();

			// ��������� ������ ��������
			if (jdbc.rs.next()) {
				pgdto.setTotalCnt(jdbc.rs.getInt(1));
				// getInt(1)�� ������ ����� ������!
			} ////// if ///////////

			// 15-2. ����Ʈ �׷�� : ��ü���� �� �������簳��
			pgdto.setListGroup(
					pgdto.getTotalCnt() / pgdto.getOnePageCnt());

			// 15-3. ���� ���ڵ�� : ��ü���� % �������簳��
			// ������ ���Ҷ� %������
			pgdto.setEtcRecord(
					pgdto.getTotalCnt() % pgdto.getOnePageCnt());

			// �Ѱ�� üũ: �������� �ְ� ���� ���� 1�����̳�
			pgdto.setLimit(
			pgdto.getEtcRecord() == 0 ? 
			pgdto.getListGroup() : pgdto.getListGroup() + 1);
			// �������� ������ 1������ �� �߰�!


		} /// try ////
		catch (Exception e) {
			e.printStackTrace();
		} /// catch ////

		// �ֿܼ� ����
		System.out.println("# ��ü����:" + pgdto.getTotalCnt() + "��");
		System.out.println("# �������簳��:" + pgdto.getOnePageCnt() + "��");
		System.out.println("# ����Ʈ �׷��:" + pgdto.getListGroup() + "��");
		System.out.println("# ���� ���ڵ��:" + pgdto.getEtcRecord() + "��");

	} /////// ������ �޼��� ///////

	
	// ����� ���� �޼��� 1
	//////////////////////////////////
	/// ���۹�ȣ ���ؼ� �����ϴ� �޼��� /////
	/////////////////////////////////
	// ����: �� ���������� ���۹�ȣ�� �ٸ��Ƿ� �̸� ���Ͽ� ������!
	public int changeStartNum(String pgnum) {
		// pgnum ������ ����Ʈ �������� �Ķ���� pgnum�� ������!
		// url/.../list.html?pgnum=3 �̺κ���!
		/**************************************** 
		[ ����¡ ����ó���� ��������ȣ�� ���۹�ȣ �����ϱ� ]
		 *****************************************/
		// ��������ȣ ��������
		String pgNum = pgnum;
		System.out.println("�Ķ����:" + pgNum);


		// �Ķ���Ͱ� ������ ���۰� ó���ϱ�
		if (pgNum != null) { // null�� �ƴϸ�!
			// �Ķ���� ����ȯ!
			try {
				pgdto.setPageSeq(Integer.parseInt(pgNum));
			} catch (NumberFormatException ex) {
				System.out.println("�Ķ���Ͱ� ���ڰ� �ƴմϴ�!<br>");
				// �⺻������ ����������!
				pgdto.setPageSeq(1);
			}
			// ���۹�ȣ ����ϱ� : �������� ���ڵ�� * (��������ȣ-1)
			pgdto.setStartNum(pgdto.getOnePageCnt() * (pgdto.getPageSeq() - 1));

		} //////////// if //////////////

		// ���۹�ȣ �����ϱ�
		return pgdto.getStartNum();

	} ///////////// changeStartNum �޼��� //////////

	// ����� ���� �޼��� 2
	/////////////////////////////
	//// ����¡ �ҽ� ���� �޼��� /////
	/////////////////////////////
	// ����: ����¡ ������ ���� ���� ��ũ�ڵ带 ���� �� html�������� ����
	public String makePaging() {
		// ����¡��ũ �ڵ� ���庯��
		String pgCode="";
		
		// 15-4. ����¡ ��ũ �ڵ� �����
		for (int i = 0; i < pgdto.getLimit(); i++) {
			// ���� ���� �������� ���� ��ȣ�� a��ũ ��������
			// b�±׷� �β��� ���� ǥ�ø� ������!
			if (i == pgdto.getPageSeq() - 1) { // i�� 0���ʹϱ� 1��
				pgCode += "<b>" + (i + 1) + "</b>";
			} /// if ////
			else {
				// pgCode������ ��� �ִ´�
				pgCode += "<a href='list.jsp?pgnum=" + (i + 1) + "'>" + (i + 1) + "</a>";
			} /// else //////

			// ���̹� ��� 
			// (�Ѱ谪-1,�� ��������ȣ �������� ���̹����)
			if (i < pgdto.getLimit() - 1) {
				pgCode += " | ";
			}

		} ////////// for //////////////
		
		// ���� ��� �����ϱ�!!!
		return pgCode;
		
	} ////////// makePaging �޼��� //////////





} ///////// Ŭ���� //////////