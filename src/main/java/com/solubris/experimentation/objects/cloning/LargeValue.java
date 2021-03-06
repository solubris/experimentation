package com.solubris.experimentation.objects.cloning;

class LargeValue implements Cloneable {
    private final int int1;
    private final int int2;
    private final int int3;
    private final int int4;
    private final long long1;
    private final long long2;
    private final long long3;
    private final long long4;
    private final double double1;
    private final double double2;
    private final double double3;
    private final double double4;
    private final String string1;
    private final String string2;
    private final String string3;
    private final String string4;

    LargeValue(int int1, int int2, int int3, int int4, long long1, long long2, long long3, long long4, double double1, double double2, double double3, double double4, String string1, String string2, String string3, String string4) {
        this.int1 = int1;
        this.int2 = int2;
        this.int3 = int3;
        this.int4 = int4;
        this.long1 = long1;
        this.long2 = long2;
        this.long3 = long3;
        this.long4 = long4;
        this.double1 = double1;
        this.double2 = double2;
        this.double3 = double3;
        this.double4 = double4;
        this.string1 = string1;
        this.string2 = string2;
        this.string3 = string3;
        this.string4 = string4;
    }

    public LargeValue(LargeValue origValue) {
        this.int1 = origValue.int1;
        this.int2 = origValue.int2;
        this.int3 = origValue.int3;
        this.int4 = origValue.int4;
        this.long1 = origValue.long1;
        this.long2 = origValue.long2;
        this.long3 = origValue.long3;
        this.long4 = origValue.long4;
        this.double1 = origValue.double1;
        this.double2 = origValue.double2;
        this.double3 = origValue.double3;
        this.double4 = origValue.double4;
        this.string1 = origValue.string1;
        this.string2 = origValue.string2;
        this.string3 = origValue.string3;
        this.string4 = origValue.string4;
    }

    public LargeValue(Builder builder) {
        this.int1 = builder.int1;
        this.int2 = builder.int2;
        this.int3 = builder.int3;
        this.int4 = builder.int4;
        this.long1 = builder.long1;
        this.long2 = builder.long2;
        this.long3 = builder.long3;
        this.long4 = builder.long4;
        this.double1 = builder.double1;
        this.double2 = builder.double2;
        this.double3 = builder.double3;
        this.double4 = builder.double4;
        this.string1 = builder.string1;
        this.string2 = builder.string2;
        this.string3 = builder.string3;
        this.string4 = builder.string4;
    }

    public static Builder from(LargeValue largeValue) {
        return new Builder().with(largeValue);
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LargeValue that = (LargeValue) o;

        if (int1 != that.int1) return false;
        if (int2 != that.int2) return false;
        if (int3 != that.int3) return false;
        if (int4 != that.int4) return false;
        if (long1 != that.long1) return false;
        if (long2 != that.long2) return false;
        if (long3 != that.long3) return false;
        if (long4 != that.long4) return false;
        if (Double.compare(that.double1, double1) != 0) return false;
        if (Double.compare(that.double2, double2) != 0) return false;
        if (Double.compare(that.double3, double3) != 0) return false;
        if (Double.compare(that.double4, double4) != 0) return false;
        if (string1 != null ? !string1.equals(that.string1) : that.string1 != null) return false;
        if (string2 != null ? !string2.equals(that.string2) : that.string2 != null) return false;
        if (string3 != null ? !string3.equals(that.string3) : that.string3 != null) return false;
        return string4 != null ? string4.equals(that.string4) : that.string4 == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = int1;
        result = 31 * result + int2;
        result = 31 * result + int3;
        result = 31 * result + int4;
        result = 31 * result + (int) (long1 ^ (long1 >>> 32));
        result = 31 * result + (int) (long2 ^ (long2 >>> 32));
        result = 31 * result + (int) (long3 ^ (long3 >>> 32));
        result = 31 * result + (int) (long4 ^ (long4 >>> 32));
        temp = Double.doubleToLongBits(double1);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(double2);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(double3);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(double4);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (string1 != null ? string1.hashCode() : 0);
        result = 31 * result + (string2 != null ? string2.hashCode() : 0);
        result = 31 * result + (string3 != null ? string3.hashCode() : 0);
        result = 31 * result + (string4 != null ? string4.hashCode() : 0);
        return result;
    }

    public int getInt1() {
        return int1;
    }

    public int getInt2() {
        return int2;
    }

    public int getInt3() {
        return int3;
    }

    public int getInt4() {
        return int4;
    }

    public long getLong1() {
        return long1;
    }

    public long getLong2() {
        return long2;
    }

    public long getLong3() {
        return long3;
    }

    public long getLong4() {
        return long4;
    }

    public double getDouble1() {
        return double1;
    }

    public double getDouble2() {
        return double2;
    }

    public double getDouble3() {
        return double3;
    }

    public double getDouble4() {
        return double4;
    }

    public String getString1() {
        return string1;
    }

    public String getString2() {
        return string2;
    }

    public String getString3() {
        return string3;
    }

    public String getString4() {
        return string4;
    }

    @Override
    public LargeValue clone() {
        try {
            return (LargeValue) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public static class Builder {
        private int int1;
        private int int2;
        private int int3;
        private int int4;
        private long long1;
        private long long2;
        private long long3;
        private long long4;
        private double double1;
        private double double2;
        private double double3;
        private double double4;
        private String string1;
        private String string2;
        private String string3;
        private String string4;

        public Builder withInt1(int int1) {
            this.int1 = int1;
            return this;
        }

        public Builder withInt2(int int2) {
            this.int2 = int2;
            return this;
        }

        public Builder withInt3(int int3) {
            this.int3 = int3;
            return this;
        }

        public Builder withInt4(int int4) {
            this.int4 = int4;
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

        public Builder withLong3(long long3) {
            this.long3 = long3;
            return this;
        }

        public Builder withLong4(long long4) {
            this.long4 = long4;
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

        public Builder withDouble3(double double3) {
            this.double3 = double3;
            return this;
        }

        public Builder withDouble4(double double4) {
            this.double4 = double4;
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

        public Builder withString3(String string3) {
            this.string3 = string3;
            return this;
        }

        public Builder withString4(String string4) {
            this.string4 = string4;
            return this;
        }

        public Builder with(LargeValue value) {
            this.int1 = value.int1;
            this.int2 = value.int2;
            this.int3 = value.int3;
            this.int4 = value.int4;
            this.long1 = value.long1;
            this.long2 = value.long2;
            this.long3 = value.long3;
            this.long4 = value.long4;
            this.double1 = value.double1;
            this.double2 = value.double2;
            this.double3 = value.double3;
            this.double4 = value.double4;
            this.string1 = value.string1;
            this.string2 = value.string2;
            this.string3 = value.string3;
            this.string4 = value.string4;
            return this;
        }

        public LargeValue buildByAllArgConstructor() {
            return new LargeValue(int1, int2, int3, int4, long1, long2, long3, long4, double1, double2, double3, double4, string1, string2, string3, string4);
        }

        public LargeValue buildByBuilderConstructor() {
            return new LargeValue(this);
        }
    }
}
