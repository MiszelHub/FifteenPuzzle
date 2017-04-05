package fifteen.graphs;


public enum Directions {
    Up{
        @Override
        public String toString() {
            return "U";
        }
    },
    Down{
        @Override
        public String toString() {
            return "D";
        }
    },
    Right{
        @Override
        public String toString() {
            return "R";
        }
    },
    Left{
        @Override
        public String toString() {
            return "L";
        }
    }


}

