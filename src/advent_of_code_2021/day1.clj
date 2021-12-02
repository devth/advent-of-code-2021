(ns advent-of-code-2021.day1 (:require [clojure.string :as string]))

(def input
  (->> (slurp "https://gist.githubusercontent.com/devth/d6a65b1377071ffd0f56b9e539266db2/raw/654dbb61ce4f9f8329212a13cb48fa203d6fdd36/aoc-2021-day1-input")
       string/split-lines (map read-string)))

(defn count-incd [[f & remaining]]
  (:incd (reduce (fn [{:keys [incd prev-line]} line]
                   {:incd (if (> line prev-line) (inc incd) incd)
                    :prev-line line})
                 {:incd 0 :prev-line f} remaining)))

(def part1 (count-incd input))

(def part2 (count-incd (map (partial reduce +) (partition 3 1 input))))
