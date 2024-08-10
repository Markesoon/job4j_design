package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .isNotEqualTo("Tetrahedron")
                .isNotEmpty();
    }

    @Test
    void isThisUNKNOWN() {
        Box box = new Box(0,0);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void isUnknownThenVertexIsMinusOne() {
        Box box = new Box(3, 5);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(-1)
                .isNotPositive();
    }

    @Test
    void getNumberOfVerticesExists() {
        Box box = new Box(8, 10);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isGreaterThan(7)
                .isNotNegative();
    }
    @Test
    void isExistsTrue() {
        Box box = new Box(4, 10);
        boolean exist = box.isExist();
        assertThat(exist).isTrue();
    }

    @Test
    void isExistsFalse() {
        Box box = new Box(3, 10);
        boolean exist = box.isExist();
        assertThat(exist).isFalse();
    }

    @Test
    void getAreaWithPrecision() {
        Box box = new Box(8,3);
        double result = box.getArea();
        assertThat(result).isGreaterThan(53.9d)
                .isLessThan(56.5d);
    }

    @Test
    void getAreaWithPercentage() {
        Box box = new Box(8,1);
        double result = box.getArea();
        assertThat(result).isEqualTo(6.0d)
                .isNotNegative();
    }
}