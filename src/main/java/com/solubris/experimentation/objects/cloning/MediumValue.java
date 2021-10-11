package com.solubris.experimentation.objects.cloning;

class MediumValue implements Cloneable {
    private final int int1;
    private final int int2;
    private final long long1;
    private final long long2;
    private final double double1;
    private final double double2;
    private final String string1;
    private final String string2;

    MediumValue(int int1, int int2, long long1, long long2, double double1, double double2, String string1, String string2) {
        this.int1 = int1;
        this.int2 = int2;
        this.long1 = long1;
        this.long2 = long2;
        this.double1 = double1;
        this.double2 = double2;
        this.string1 = string1;
        this.string2 = string2;
    }

    public MediumValue(MediumValue origValue) {
        this.int1 = origValue.int1;
        this.int2 = origValue.int2;
        this.long1 = origValue.long1;
        this.long2 = origValue.long2;
        this.double1 = origValue.double1;
        this.double2 = origValue.double2;
        this.string1 = origValue.string1;
        this.string2 = origValue.string2;
    }

    public MediumValue(Builder builder) {
        this.int1 = builder.int1;
        this.int2 = builder.int2;
        this.long1 = builder.long1;
        this.long2 = builder.long2;
        this.double1 = builder.double1;
        this.double2 = builder.double2;
        this.string1 = builder.string1;
        this.string2 = builder.string2;
    }

    public static Builder from(MediumValue mediumValue) {
        return new Builder().with(mediumValue);
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getInt1() {
        return int1;
    }

    public int getInt2() {
        return int2;
    }

    public long getLong1() {
        return long1;
    }

    public long getLong2() {
        return long2;
    }

    public double getDouble1() {
        return double1;
    }

    public double getDouble2() {
        return double2;
    }

    public String getString1() {
        return string1;
    }

    public String getString2() {
        return string2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MediumValue that = (MediumValue) o;

        if (int1 != that.int1) return false;
        if (int2 != that.int2) return false;
        if (long1 != that.long1) return false;
        if (long2 != that.long2) return false;
        if (Double.compare(that.double1, double1) != 0) return false;
        if (Double.compare(that.double2, double2) != 0) return false;
        if (string1 != null ? !string1.equals(that.string1) : that.string1 != null) return false;
        return string2 != null ? string2.equals(that.string2) : that.string2 == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = int1;
        result = 31 * result + int2;
        result = 31 * result + (int) (long1 ^ (long1 >>> 32));
        result = 31 * result + (int) (long2 ^ (long2 >>> 32));
        temp = Double.doubleToLongBits(double1);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(double2);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (string1 != null ? string1.hashCode() : 0);
        result = 31 * result + (string2 != null ? string2.hashCode() : 0);
        return result;
    }

    @Override
    public MediumValue clone() {
        try {
            return (MediumValue) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public static class Builder {
        private int int1;
        private int int2;
        private long long1;
        private long long2;
        private double double1;
        private double double2;
        private String string1;
        private String string2;

        public Builder withInt1(int int1) {
            this.int1 = int1;
            return this;
        }

        public Builder withInt2(int int2) {
            this.int2 = int2;
            return this;
        }

        public Builder withLong1(long long1) {
            this.long1 = long1;
            return this;
        }

        public Builder withLong2(long long2) {
            this.long2 = long2;
            return this;
        }

        public Builder withDouble1(double double1) {
            this.double1 = double1;
            return this;
        }

        public Builder withDouble2(double double2) {
            this.double2 = double2;
            return this;
        }

        public Builder withString1(String string1) {
            this.string1 = string1;
            return this;
        }

        public Builder withString2(String string2) {
            this.string2 = string2;
            return this;
        }

        public Builder with(MediumValue value) {
            this.int1 = value.int1;
            this.int2 = value.int2;
            this.long1 = value.long1;
            this.long2 = value.long2;
            this.double1 = value.double1;
            this.double2 = value.double2;
            this.string1 = value.string1;
            this.string2 = value.string2;
            return this;
        }

        public MediumValue buildByAllArgConstructor() {
            return new MediumValue(int1, int2, long1, long2, double1, double2, string1, string2);
        }

        public MediumValue buildByBuilderConstructor() {
            return new MediumValue(this);
        }
    }
}
