package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 고려.
 */
public class MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance(){
        return instance;
    }
    private MemberRepository() {
        //private 로 생성자를 막아버림.
    }
    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }
    public Member findById(Long id){
        return store.get(id);
    }
    public List<Member> findAll(){
        //store에 있는 value list를 건들고 싶지 않아서 ArrayList로 변환하여 리턴.
        return new ArrayList<>(store.values());
    }
    public void clearStore(){
        store.clear();
    }
}
