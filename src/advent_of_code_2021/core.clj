(ns advent-of-code-2021.core
  (:require
   [clojure.string :as string]
   [clojure.java.io :as io]))

(def input
  (->> (io/resource "data/day-1-input.txt")
       slurp
       string/split-lines
       (map read-string)))

(defn count-increased [coll]
  (:increased
   (reduce
    (fn [{:keys [increased prev-line] :as acc} line]
      (assoc
       acc
       :increased (if (and prev-line (> line prev-line))
                    (inc increased)
                    increased)
       :prev-line line))
    {:increased 0 :prev-line nil}
    coll)))

(def part1 (count-increased input))

(def part2 (count-increased (map (partial reduce +) (partition 3 1 input))))
