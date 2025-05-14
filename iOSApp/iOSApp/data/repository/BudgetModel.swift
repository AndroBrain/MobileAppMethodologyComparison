//
//  BudgetModel.swift
//  iOSApp
//
//  Created by Michal Ankiersztajn on 14/05/2025.
//

import Foundation

struct BudgetModel : Hashable, Identifiable {
    var amount: Int64
    var content: String
    var id: UUID
    var isSpending: Bool
}
