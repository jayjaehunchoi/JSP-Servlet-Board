import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import domain.member.Member;
import domain.member.MemberRepository;
import domain.member.MemberStatus;
import service.MemberService;
import utils.ShaEncoder;


class MemberTest {
	MemberService memberService = MemberService.getInstance();
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void memberCreateTest() {
		Member member = new Member("wogns0108","qwer1234!","wogns0108@naver.com"); 
		member.createSalt();
		memberService.join(member);
		Member findMember = memberService.findByLoginId("wogns0108");
		
		Assertions.assertEquals(MemberStatus.EXISTED, findMember.getMemberStatus());

	}
	
	@Test
	void memberDeleteTest() {
		Member member = memberService.findById(10L);
		int res = memberService.deleteMember(member);
		Assertions.assertEquals(1, res);
		
		Member findMember = memberService.findById(10L);
		Assertions.assertEquals(MemberStatus.WITHDRAWL,findMember.getMemberStatus());
		Assertions.assertEquals("Å»ÅðÈ¸¿ø", findMember.getEmail());
		Assertions.assertEquals("Å»ÅðÈ¸¿ø", findMember.getLoginId());
	}
	
	@Test
	void memberStatusTest() {
		Member findMember = memberService.findById(10L);
		Assertions.assertEquals(MemberStatus.WITHDRAWL,findMember.getMemberStatus());
	}
	
	@Test
	void memberFindTest() {
		Member member = new Member("wogns0108","qwer1234!","wogns0108@naver.com"); 
		member.createSalt();
		memberService.join(member);
		
		Member findMemberWithId = memberService.findById(11L);
		Member findMemberWithLoginId = memberService.findByLoginId("wogns0108");
		Member findMemberWithEmail = memberService.findByEmail("wogns0108@gmail.com");
		
		Assertions.assertEquals(member.getLoginId(), findMemberWithLoginId.getLoginId());
		Assertions.assertEquals(member.getLoginId(), findMemberWithEmail.getLoginId());
		Assertions.assertEquals(member.getLoginId(), findMemberWithId.getLoginId());
		
	}
	
	@Test
	void memberUpdateTest() {
		Member findMember = memberService.checkIdAndPassword("wogns0108", "qwer1234!");
		Assertions.assertEquals("wogns0108", findMember.getLoginId());
		
		findMember.setPassword("qwer12345!");
		findMember.setEmail("wogns0108@gmail.com");
		int res = memberService.updateMember(findMember);
		
		Member findMemberAfterUpdate = memberService.findById(11L);
		assertEquals(1, res);
		assertEquals(new ShaEncoder().transferToSHA_256("qwer12345!", findMember.getSalt()), findMemberAfterUpdate.getPassword());
		assertEquals("wogns0108@gmail.com", findMemberAfterUpdate.getEmail());
	}

}
