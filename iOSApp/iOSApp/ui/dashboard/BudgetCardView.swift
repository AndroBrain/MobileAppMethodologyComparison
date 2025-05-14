//
//  BudgetCardView.swift
//  iOSApp
//
//  Created by Michal Ankiersztajn on 14/05/2025.
//
import SwiftUI

struct BudgetCardView : View {
    var budget: BudgetModel
    var amount: Double
    
    init(budget: BudgetModel) {
        self.budget = budget
        amount = Double(budget.amount) / 100
        if budget.isSpending {
            amount *= -1
        }
    }
    
    var body: some View {
        VStack(alignment: .leading, spacing: 5) {
            Text(String(format: "%.2f zł", amount))
                .lineLimit(1)
                .font(.title3)
                .fontWeight(.bold)
            Text(budget.content)
        }
    }
}
