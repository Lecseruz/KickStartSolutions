package latestguest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Solution {
    private static class Pair {
        private int consulateIndex;
        private String direction;

        private Pair(int consulateIndex, String direction) {
            this.consulateIndex = consulateIndex;
            this.direction = direction;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return consulateIndex == pair.consulateIndex &&
                    Objects.equals(direction, pair.direction);
        }

        @Override
        public int hashCode() {
            return Objects.hash(consulateIndex, direction);
        }
    }

    private static class Bus {
        private int consulate;
        private String value;
        private List<Guest> guestList = new ArrayList<>();

        private Bus() {
        }

        private List<Guest> getGuestList() {
            return guestList;
        }

        private int getConsulate() {
            return consulate;
        }

        private void setConsulate(int consulate) {
            this.consulate = consulate;
        }

        private String getValue() {
            return value;
        }

        private void setValue(String value) {
            this.value = value;
        }
    }

    private static class Consulate {
        private int time;
        private List<Bus> busList = new ArrayList<>();

        private Consulate(int time) {
            this.time = time;
        }

        private List<Bus> getBusList() {
            return busList;
        }

        private int getTime() {
            return time;
        }

        private void setTime(int time) {
            this.time = time;
        }
    }

    private static class Guest {
        private int id;

        private Guest(int id) {
            this.id = id;
        }

        private int getId() {
            return id;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            int g = in.nextInt();
            int m = in.nextInt();
            Map<Pair, Bus> busMap = new HashMap<>();
            Map<Integer, Consulate> consulateHashMap = new HashMap<>();
            for (int j = 1; j <= n; ++j) {
                consulateHashMap.put(j, new Consulate(0));
            }
            for (int j = 1; j <= g; ++j) {
                int consulateIndex = in.nextInt();
                String direction = in.next();
                Guest guest = new Guest(j);
                busMap.computeIfPresent(new Pair(consulateIndex, direction), (key, value) -> {
                    value.getGuestList().add(guest);
                    return value;
                });
                if (busMap.get(new Pair(consulateIndex, direction)) == null) {
                    Bus bus = new Bus();
                    bus.setValue(direction);
                    bus.setConsulate(consulateIndex);
                    bus.getGuestList().add(guest);
                    busMap.putIfAbsent(new Pair(consulateIndex, direction), bus);
                    Consulate consulate = consulateHashMap.get(consulateIndex);
                    consulate.getBusList().add(bus);
                }
            }
            for (int j = 1; j <= m; ++j) {
                for (Bus bus : busMap.values()) {
                    int consulateIndex = bus.getConsulate();
                    if (bus.getValue().equals("A")) {
                        if (consulateIndex == 1)
                            consulateIndex = n;
                        else
                            --consulateIndex;
                    } else {
                        if (consulateIndex == n)
                            consulateIndex = 1;
                        else
                            ++consulateIndex;
                    }
                    bus.setConsulate(consulateIndex);
                    Consulate consulate = consulateHashMap.get(consulateIndex);
                    if (consulate.getTime() != j) {
                        consulate.getBusList().clear();
                    }
                    consulate.setTime(j);
                    consulate.getBusList().add(bus);
                }
            }
            Map<Integer, Integer> integerMap = new HashMap<>();
            for (int j = 1; j <= g; ++j) {
                integerMap.put(j, 0);
            }
            for (int j = 1; j <= n; ++j)
                consulateHashMap.get(j).getBusList().forEach(bus ->
                        bus.getGuestList().forEach(guest ->
                                integerMap.computeIfPresent(guest.getId(), (key, value) -> ++value)
                        ));
            System.out.print("Case #" + i + ":");
            for (int j = 1; j <= g; ++j) {
                int count = integerMap.get(j);
                System.out.print(" " + count);
            }
            System.out.println();
        }
    }
}
