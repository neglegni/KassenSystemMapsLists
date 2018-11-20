import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import static de.hawhh.informatik.maps.MapUtils.*;
import static org.junit.Assert.*;

public class MapUtilityTest {
    Map<Integer,Integer> mii;
    Map<Integer,Integer> miiSameKeyVals;
    Map<Integer,Integer> miiSameKeys;
    Map<Integer,Integer> miiIntersectSameKeyVals = new HashMap<>();
    Map<Integer, Integer> emptyMap = new HashMap<>();
    Map<Integer,Integer> miiUnionSameKeyVals = new HashMap<>();
    Map<Integer, Integer> miiUnionSameKeys = new HashMap<>();
    Map<Integer, Integer> miiSameKeyValsComplementMii = new HashMap<>();
    Map<Integer, Set<Integer>> miiIntersectSameKeyValsJoin = new HashMap<>();
    Map<Integer, Set<Integer>> miiIntersectSameKeysJoin = new HashMap<>();
    Map<Integer, Set<Integer>> miiUnionJoinVals = new HashMap<>();
    Map<Integer, Set<Integer>> miiUnionJoinSameKeySameKeyVals = new HashMap<>();

    Random generator = new Random(46782);

    @Before
    public void initData(){

        mii = new HashMap<>();
        List<Integer> li2 = Arrays.asList(4,5,6);
        for(int i : Arrays.asList(1,2,3)) {
            mii.put(i,li2.get(i-1));
        }
        mii.put(0,3);

        miiSameKeyVals = new HashMap<>();
        for(int i : Arrays.asList(1,2,3,4,5)) {
            miiSameKeyVals.put(i,li2.get((i-1)%3));
        }

        miiSameKeys = new HashMap<>();
        for(int i : Arrays.asList(1,2,3,4,5)) {
            miiSameKeys.put(i,
                    li2.get(generator.nextInt(3)) + generator.nextInt(10));
        }

        miiIntersectSameKeyVals.put(1,4);
        miiIntersectSameKeyVals.put(2,5);
        miiIntersectSameKeyVals.put(3,6);

        miiIntersectSameKeyValsJoin.put(1,new HashSet<>(Arrays.asList(4)));
        miiIntersectSameKeyValsJoin.put(2,new HashSet<>(Arrays.asList(5)));
        miiIntersectSameKeyValsJoin.put(3,new HashSet<>(Arrays.asList(6)));

        miiIntersectSameKeysJoin.put(1,new HashSet<>(Arrays.asList(4,6)));
        miiIntersectSameKeysJoin.put(2,new HashSet<>(Arrays.asList(5,12)));
        miiIntersectSameKeysJoin.put(3,new HashSet<>(Arrays.asList(6,15)));

        miiUnionSameKeyVals.putAll(mii);
        miiUnionSameKeyVals.putAll(miiSameKeyVals);

        miiUnionSameKeys.put(4,5);
        miiUnionSameKeys.put(5,9);
        miiUnionSameKeys.put(0,3);

        miiUnionJoinVals = mii.entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey(), entry -> new HashSet<>(Arrays.asList(entry.getValue()))));

        miiUnionJoinSameKeySameKeyVals.put(1,new HashSet<>(Arrays.asList(4,6)));
        miiUnionJoinSameKeySameKeyVals.put(2,new HashSet<>(Arrays.asList(5,12)));
        miiUnionJoinSameKeySameKeyVals.put(3,new HashSet<>(Arrays.asList(6,15)));
        miiUnionJoinSameKeySameKeyVals.put(4,new HashSet<>(Arrays.asList(4,5)));
        miiUnionJoinSameKeySameKeyVals.put(5,new HashSet<>(Arrays.asList(5,9)));

        miiSameKeyValsComplementMii.put(4,4);
        miiSameKeyValsComplementMii.put(5,5);

    }

    @Test
    public void testIntersectPos() {
//        System.out.println(mii);
//        System.out.println(miiSameKeyVals);
//        System.out.println(miiIntersectSameKeyVals);
       assertEquals(mii,intersect(mii,mii));
       assertEquals(miiIntersectSameKeyVals, intersect(mii, miiSameKeyVals));
    }

    @Test
    public void testIntersectBoundary() {
//       System.out.println(mii);
//       System.out.println(miiSameKeys);
//        System.out.println(emptyMap);
        assertEquals(emptyMap,intersect(mii,miiSameKeys));
        assertEquals(emptyMap,intersect(miiSameKeys,miiSameKeyVals));
        assertEquals(emptyMap,intersect(miiSameKeys,emptyMap));
        assertEquals(emptyMap,intersect(emptyMap,miiSameKeys));
    }

    @Test
    public void testIntersectJoinValsPos(){
//              System.out.println(mii);
//      System.out.println(miiSameKeys);
//        System.out.println(miiIntersectSameKeysJoin);
        assertEquals(miiIntersectSameKeyValsJoin, intersectJoinValues(mii, miiSameKeyVals));
        assertEquals(miiIntersectSameKeyValsJoin,intersectJoinValues(miiSameKeyVals,mii));
        assertEquals(miiIntersectSameKeysJoin,intersectJoinValues(mii,miiSameKeys));
        assertEquals(miiIntersectSameKeysJoin,intersectJoinValues(miiSameKeys,mii));
    }

    @Test
    public void testIntersectJoinValsBoundary(){
        assertEquals(emptyMap, intersectJoinValues(mii, emptyMap));
        assertEquals(emptyMap,intersectJoinValues(emptyMap,mii));
    }


    @Test
    public void testUnionPos(){

        assertEquals(miiUnionSameKeyVals,union(mii,miiSameKeyVals));
        assertEquals(miiUnionSameKeyVals,union(miiSameKeyVals,mii));
        assertEquals(miiUnionSameKeys,union(mii,miiSameKeys));
        assertEquals(miiUnionSameKeys,union(miiSameKeys,mii));
    }

    @Test
    public void testUnionBoundary(){
        assertEquals(new HashMap(mii),union(mii,emptyMap));
        assertEquals(new HashMap(mii),union(emptyMap,mii));
        assertEquals(emptyMap,union(miiSameKeyVals,miiSameKeys));
        assertEquals(emptyMap,union(miiSameKeys,miiSameKeyVals));
    }

    @Test
    public void testUnionJoinValuesPos(){
        assertEquals(miiUnionJoinSameKeySameKeyVals, unionJoinValues(miiSameKeys,miiSameKeyVals));
        assertEquals(miiUnionJoinSameKeySameKeyVals, unionJoinValues(miiSameKeyVals,miiSameKeys));
    }
    @Test
    public void testUnionJoinValuesBoundary(){
        assertEquals(miiUnionJoinVals,unionJoinValues(mii,emptyMap));
        assertEquals(miiUnionJoinVals,unionJoinValues(emptyMap,mii));
        assertEquals(miiUnionJoinVals,unionJoinValues(mii,mii));
    }

    @Test
    public void testComplementPos(){
        assertEquals(miiSameKeyValsComplementMii,complement(miiSameKeyVals,mii));
        assertEquals(miiSameKeys,complement(miiSameKeys,miiSameKeyVals));
        assertEquals(miiSameKeyVals,complement(miiSameKeyVals,miiSameKeys));
        Map<Integer,Integer> mapZeroThree = new HashMap<>();
        mapZeroThree.put(0,3);
        assertEquals(mapZeroThree,complement(mii,miiSameKeyVals));
    }

    @Test
    public void testComplementBoundary(){
        assertEquals(emptyMap,complement(mii,mii));
        assertEquals(miiSameKeys,complement(miiSameKeys,emptyMap));
        assertEquals(emptyMap,complement(emptyMap,miiSameKeys));
    }

    @Test
    public void testIsSubMapPos(){
        assertTrue(isSubMap(miiSameKeyVals,miiSameKeyVals));
        miiSameKeyVals.put(0,3);
        assertTrue(isSubMap(mii,miiSameKeyVals));
    }

    @Test
    public void testIsSubMapNeg(){
        assertFalse(isSubMap(mii,miiSameKeys));
        System.out.println(mii);
        System.out.println(miiSameKeys);

        assertFalse(isSubMap(miiSameKeyVals,mii));
    }

    @Test
    public void testIsSubMapBoundary(){
        assertTrue(isSubMap(emptyMap,miiSameKeys));
    }
}
