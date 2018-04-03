(ns game-of-life.core-test
  (:require [clojure.test :refer :all]
            [game-of-life.core :refer :all]))

(deftest test-1
  (testing "Any live cell with fewer than two live neighbours dies, as if caused by underpopulation"
    (let [generation [[true true false]
                      [false false false]
                      [false false false]]]
      (is (= (lives? generation 0 0)  false)))))

(deftest test-2
  (testing "Any live cell with two live neighbours lives on to the next generation"
    (let [generation [[true true false]
                      [true false false]
                      [false false false]]]
      (is (= (lives? generation 0 0) true)))))

(deftest test-3
  (testing "Any live cell with three live neighbours lives on to the next generation"
    (let [generation [[true true false]
                      [true true false]
                      [false false false]]]
      (is (= (lives? generation 0 0) true)))))

(deftest test-4
  (testing "Any live cell with more than three live neighbours dies, as if by overpopulation"
    (let [generation [[true true true]
                      [true true false]
                      [false false false]]]
      (is (= (lives? generation 0 1) false)))))

(deftest test-5
  (testing "Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction"
    (let [generation [[false true false]
                      [true true false]
                      [false false false]]]
      (is (= (lives? generation 0 0) true)))))
