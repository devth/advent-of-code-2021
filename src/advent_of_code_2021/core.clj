(ns advent-of-code-2021.core
  (:require
   [clojure.string :as string]
   [clojure.java.io :as io]))

(def input
  (->> (io/resource "data/day-1-input.txt")
       slurp
       string/split-lines
       (map read-string)))

(count input)

(reduce
 (fn [{:keys [increased prev-line] :as acc} line]
   (prn (assoc acc :line line
               :increased (> line prev-line)
               ))
   (assoc
    acc
    :increased (if (and prev-line (> line prev-line)) (inc increased) increased)
    :prev-line line))
 {:increased 0 :prev-line nil}
 input)
