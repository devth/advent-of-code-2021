(ns advent-of-code-2021.day2 (:require [clojure.string :as string]))

(defn parse-line [line]
  (let [[dir mag] (string/split line #" ")]
    [(keyword dir) (read-string mag)]))

(def input
  (->> (slurp "https://gist.githubusercontent.com/devth/7a1dc029f0fe059f14d1c93aef3c79b8/raw/1356ee4fac9f8820688e983112ee2ea0382f027c/aoc-2021-day2-input")
       string/split-lines (map parse-line)))

(defn compute
 [effects]
  (reduce
   (fn [acc [dir mag]] ((effects dir) acc mag))
   {:horz 0 :depth 0 :aim 0}
   input))

;; part one

(def p1-effects
  {:forward #(update %1 :horz (partial + %2))
   :down #(update %1 :depth (partial + %2))
   :up #(update %1 :depth (fn [p-depth] (- p-depth %2)))})

(def p1-res (compute p1-effects))

(def p1 (* (:horz part1-res) (:depth part1-res)))

;; part two

(def p2-effects
  {:forward (fn [{:keys [aim] :as acc} x]
              (-> acc
                  (update :horz (partial + x))
                  (update :depth (partial + (* aim x)))))
   :down #(update %1 :aim (partial + %2))
   :up #(update %1 :aim (fn [p-aim] (- p-aim %2)))})

(def p2-res (compute p2-effects))

(def p2 (* (:horz part2-res) (:depth part2-res)))
