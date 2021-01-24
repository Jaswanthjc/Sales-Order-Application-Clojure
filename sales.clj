(ns democlojure.sales
  (:require [clojure.string :as str]))


;Option 1
(def file_lines "")
(def cust_map {})
(def sorted_cust_map{})

(def cust_file "cust.txt")

(defn getLines [file]
  (def file_lines (clojure.string/split-lines (slurp file))))
(getLines cust_file)


(doseq [i file_lines]

  (def string-split (clojure.string/split i #"\|"))
  (def cust_map (assoc cust_map (read-string (get string-split 0)) (rest string-split)))
  )

(defn display_CustTable []
  (def sorted_cust_map(into (sorted-map) cust_map))
  (doseq [[k v] (map vector (keys sorted_cust_map) (vals sorted_cust_map))]
    (println (str k ":" v))
    )
  )

;Option 2
(def file_lines "")
(def prod_map {})
(def sorted_prod_map{})

(def prod_file "prod.txt")

(defn getLines [file]
  (def file_lines (clojure.string/split-lines (slurp file))))
(getLines prod_file)


(doseq [i file_lines]

  (def string-split (clojure.string/split i #"\|"))
  (def prod_map (assoc prod_map (read-string (get string-split 0)) (rest string-split) ))
  )
(defn display_ProdTable []
  (def sorted_prod_map(into (sorted-map) prod_map))
  (doseq [[k v] (map vector (keys sorted_prod_map) (vals sorted_prod_map))]
    (println (str k ":" v))
    )
  )

;Option 3
(def sales_file "sales.txt")

(defn getLines [file]
  (def file_lines (clojure.string/split-lines (slurp file))))
(getLines sales_file)

(def sales_cust_file "cust.txt")

(defn getLines [file]
  (def file_lines1 (clojure.string/split-lines (slurp file))))
(getLines sales_cust_file)

(def sales_prod_file "prod.txt")

(defn getLines [file]
  (def file_lines2 (clojure.string/split-lines (slurp file))))
(getLines sales_prod_file)


(def sales_cust_map {})
(def sales_prod_map {})
(def sales_item_map {})
(def origi_sales_cust_map {})
(def origi_sales_prod_map {})


(doseq [i file_lines]

  (def string-split (clojure.string/split i #"\|"))
  (def sales_cust_map (assoc sales_cust_map (get string-split 0) (get string-split 1)))
  (def sales_prod_map (assoc sales_prod_map (get string-split 0) (get string-split 2)))
  (def sales_item_map (assoc sales_item_map (get string-split 0) (read-string (get string-split 3))))
  )
(def s_sales_cust_map (into (sorted-map)sales_cust_map))

(doseq [x file_lines1]
  (def string-split (clojure.string/split x #"\|"))
  (def origi_sales_cust_map (assoc origi_sales_cust_map (get string-split 0) (get string-split 1)))
  )

(doseq [y file_lines2]
  (def string-split (clojure.string/split y #"\|"))
  (def origi_sales_prod_map (assoc origi_sales_prod_map (get string-split 0) (get string-split 1)))
  )

(defn display_SaleTable[]

  (doseq [[k v] (map vector (keys s_sales_cust_map) (vals s_sales_cust_map))]
    (def proNo (get sales_prod_map k))
    (def proName (get origi_sales_prod_map proNo))
    (def sales_tab (str k ":(" (get origi_sales_cust_map v) "," proName "," (get sales_item_map k) ")"))
    (println sales_tab)
    )
  )

;Option 4 and Option 5
(def sum_prod_file "prod.txt")

(defn getLines [file]
  (def file_lines10 (clojure.string/split-lines (slurp file))))
(getLines sum_prod_file)

(def sum_sales_file "sales.txt")

(defn getLines [file]
  (def file_lines11 (clojure.string/split-lines (slurp file))))
(getLines sum_sales_file)

(def sum_cust_file "cust.txt")

(defn getLines [file]
  (def file_lines12 (clojure.string/split-lines (slurp file))))
(getLines sum_cust_file)

(def sum_sales_item_map {})
(def sum_sales_prod_map {})
(def sum_sales_cust_map {})
(def sum_prod_cost_map {})
(def sum_prod_name_map {})
(def sum_cust_name_map {})
(def sum_total_sales_cust_Map {})
(def sum_total_sales_product_Map {})
(def final_total_sumsales_cust_Map {})
(def final_total_sumproduct_Map {})


(doseq [i file_lines10]

  (def string-split1 (clojure.string/split i #"\|"))
  (def sum_prod_name_map (assoc sum_prod_name_map (get string-split1 0) (get string-split1 1)))
  (def sum_prod_cost_map (assoc sum_prod_cost_map (get string-split1 0) (read-string(get string-split1 2)) ))
  )

(doseq [i file_lines11]

  (def string-split1 (clojure.string/split i #"\|"))
  (def sum_sales_item_map (assoc sum_sales_item_map (get string-split1 0) (read-string(get string-split1 3)) ))
  (def sum_sales_cust_map (assoc sum_sales_cust_map (get string-split1 0) (get string-split1 1) ))
  (def sum_sales_prod_map (assoc sum_sales_prod_map (get string-split1 0) (get string-split1 2) ))
  )

(doseq [i file_lines12]

  (def string-split1 (clojure.string/split i #"\|"))
  (def sum_cust_name_map (assoc sum_cust_name_map (get string-split1 0) (get string-split1 1) ))
  )


(
  doseq [[k v] (map vector (keys sum_sales_cust_map) (vals sum_sales_cust_map))]

  (def customerName (get sum_cust_name_map v))
  (def productId (get sum_sales_prod_map k))
  (def productName (get sum_prod_name_map productId))
  (def productPrice (get sum_prod_cost_map productId))
  (def productvalue (get sum_sales_item_map k))
  (def intialpurchase (* productPrice productvalue))
  (def totalPurchaseValue (+ intialpurchase (get sum_total_sales_cust_Map customerName 0.0)))
  (def sum_total_sales_cust_Map (assoc sum_total_sales_cust_Map customerName totalPurchaseValue))

  (def total_product_count (+ productvalue (get sum_total_sales_product_Map productName 0.0)))
  (def sum_total_sales_product_Map (assoc sum_total_sales_product_Map productName total_product_count))

  )
(doseq [[k v] (map vector (keys sum_cust_name_map) (vals sum_cust_name_map))]
  (def final_total_sumsales_cust_Map (assoc final_total_sumsales_cust_Map v (get sum_total_sales_cust_Map v 0)))
  )

(doseq [[k v] (map vector (keys sum_prod_name_map) (vals sum_prod_name_map))]
  (def final_total_sumproduct_Map (assoc final_total_sumproduct_Map v (get sum_total_sales_product_Map v 0)))
  )


(defn totalSaleforCust[]

  (println "Please enter customer name:")
  (def customerName (read-line))

  (if (contains? final_total_sumsales_cust_Map customerName)
    (do (println (str customerName ": CAD "(get final_total_sumsales_cust_Map customerName)))
        true)
    (do (println "Wrong customer name entered.")
        (totalSaleforCust))
    )
  )

(defn totalCountforProd[]

  (println "Please enter product name:")
  (def prodName (read-line))

  (if (contains? final_total_sumproduct_Map prodName)
    (do (println (str prodName ": "(get final_total_sumproduct_Map prodName)))
        true)
    (do (println "Wrong product name entered.")
        (totalCountforProd))
    )
  )

(defn Programexit[]
  (println "Good Bye!")
  (System/exit 0)
  )



(defn MainMenu []
  (def menu (str "\n* Sales Menu *\n--------------\n
  1. Display Customer Table\n
  2. Display Product Table\n
  3. Display Sales Table\n
  4. Total Sales for Customer\n
  5. Total Count for Product\n
  6. Exit\n
  Enter your choice?"))
  (println menu)

  (let [userChoice (read-line)]
    (case userChoice
      "1" (display_CustTable)
      "2" (display_ProdTable)
      "3" (display_SaleTable)
      "4" (totalSaleforCust)
      "5" (totalCountforProd)
      "6" (Programexit)
      (println "You entered wrong choice. Please select the right choice.")))

    (MainMenu)
  )

(MainMenu)