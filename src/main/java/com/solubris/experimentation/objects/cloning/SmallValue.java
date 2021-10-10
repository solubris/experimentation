package com.solubris.experimentation.objects.cloning;

class SmallValue implements Cloneable {
    private final int theInt;
    private final long theLong;
    private final double theDouble;
    private final String theString;

    SmallValue(int theInt, long theLong, double theDouble, String theString) {
        this.theInt = theInt;
        this.theLong = theLong;
        this.theDouble = theDouble;
        this.theString = theString;
    }

    public SmallValue(SmallValue origValue) {
        this.theInt = origValue.theInt;
        this.theLong = origValue.theLong;
        this.theDouble = origValue.theDouble;
        this.theString = origValue.theString;
    }

    public static Builder from(SmallValue value) {
        return new Builder()
                .withTheInt(value.getTheInt())
                .withTheLong(value.getTheLong())
                .withTheDouble(value.getTheDouble())
                .withTheString(value.getTheString());
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getTheInt() {
        return theInt;
    }

    public long getTheLong() {
        return theLong;
    }

    public double getTheDouble() {
        return theDouble;
    }

    public String getTheString() {
        return theString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SmallValue value = (SmallValue) o;

        if (theInt != value.theInt) return false;
        if (theLong != value.theLong) return false;
        if (Double.compare(value.theDouble, theDouble) != 0) return false;
        return theString != null ? theString.equals(value.theString) : value.theString == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = theInt;
        result = 31 * result + (int) (theLong ^ (theLong >>> 32));
        temp = Double.doubleToLongBits(theDouble);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (theString != null ? theString.hashCode() : 0);
        return result;
    }

    @Override
    public SmallValue clone() {
        try {
            return (SmallValue) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public static class Builder {
        private int theInt;
        private long theLong;
        private double theDouble;
        private String theString;

        public Builder withTheInt(int theInt) {
            this.theInt = theInt;
            return this;
        }

        public Builder withTheLong(long theLong) {
            this.theLong = theLong;
            return this;
        }

        public Builder withTheDouble(double theDouble) {
            this.theDouble = theDouble;
            return this;
        }

        public Builder withTheString(String theString) {
            this.theString = theString;
            return this;
        }

        public SmallValue build() {
            return new SmallValue(theInt, theLong, theDouble, theString);
        }
    }
}
