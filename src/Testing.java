import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class Testing{
    @Nested
    class PresetTesting {
        @Nested
        class AmericaTests{
            private Simulation sim;
            private HashMap<String,Region> regions;
            @BeforeEach
            void setup(){
                sim = new Simulation("./presetFiles/AmericaPreset.txt");
                regions = sim.getRegions();
            }

            @Test
            void numStates(){
                assert regions.size() == 50;
            }

            @Test
            void populations(){
                for (Region r : regions.values()){
                    assert r.population > 0;
                }
            }

            @Test
            void typos(){
                for (Region r : regions.values()){
                    for (String n : r.neighbors){
                        assert regions.containsKey(n);
                    }
                }
            }

            @Test
            void unDirected(){
                for (String r : regions.keySet()){
                    for (String n : regions.get(r).neighbors){
                        //for all neighbors of r
                        boolean symmetric = false;
                        for (String nn : regions.get(n).neighbors){
                            //test that the neighbors of the neighbor contains r
                            if (nn.equals(r)){ // rest
                                symmetric = true;
                                break;
                            }
                        }

                        assert symmetric;
                    }
                }
            }

            @Test
            void noSelfEdges(){
                for (String r : regions.keySet()) {
                    for (String n : regions.get(r).neighbors) {
                        assert (!n.equals(r)); //neighbors doesn't contain itself
                    }
                }
            }

            @Test
            void connected(){
                //breadth first search
                Set<String> discovered = new HashSet<String>();
                Queue<String> unvisited = new LinkedList<String>();
                unvisited.add(regions.keySet().iterator().next());
                discovered.add(unvisited.peek());
                while (!unvisited.isEmpty()){
                    String r = unvisited.poll();
                    for (String n : regions.get(r).neighbors){
                        if (!discovered.contains(n)){
                            discovered.add(n);
                            unvisited.add(n);
                        }
                    }
                }
                //connected
                assert discovered.size() == regions.size();
            }
        }

    }
}
