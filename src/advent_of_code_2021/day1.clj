(ns advent-of-code-2021.day1
  (:require
   [clojure.string :as string]
   [clojure.java.io :as io]))

(def input
  (->> (io/resource "data/day-1-input.txt")
       slurp string/split-lines (map read-string)))

(defn count-incd [coll]
  (:incd
   (reduce
    (fn [{:keys [incd prev-line] :as acc} line]
      (assoc acc :incd (if (and prev-line (> line prev-line)) (inc incd) incd)
             :prev-line line))
    {:incd 0 :prev-line nil}
    coll)))

(def part1 (count-incd input))

(def part2 (count-incd (map (partial reduce +) (partition 3 1 input))))
