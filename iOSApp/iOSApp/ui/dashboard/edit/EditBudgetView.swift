//
//  EditBudgetView.swift
//  iOSApp
//
//  Created by Michal Ankiersztajn on 14/05/2025.
//

import SwiftUI

struct EditBudgetView : View {
    @EnvironmentObject var vm: DashboardViewModel
    @State var budget : BudgetModel
    
    @State var content: String = ""
    @State var isSpending: Bool = false
    @State var amount : Double = 0.0
    let formatter: NumberFormatter = {
        let formatter = NumberFormatter()
        formatter.numberStyle = .decimal
        formatter.maximumFractionDigits = 2
        return formatter
    }()
    
    var body : some View {
        VStack(alignment: .leading, spacing: 20) {
            Toggle("Czy wydatek?", isOn: $isSpending)
            TextField("Kwota", value: $amount, formatter: formatter)
                .padding(8)
                .overlay(RoundedRectangle(cornerRadius: 16).stroke(.secondary, lineWidth: 2))
            TextField("Opis", text: $content)
                .padding(8)
                .overlay(RoundedRectangle(cornerRadius: 16).stroke(.secondary, lineWidth: 2))
            Spacer()
        }
        .padding(20)
        .toolbar {
            ToolbarItem(placement: .topBarTrailing) {
                Button("Zapisz") {
                    let b = BudgetModel(amount: Int64(amount * 100), content: content, id: budget.id, isSpending: isSpending)
                    vm.updateNote(budget: b)
                }
            }
        }
        .onAppear {
            self.isSpending = budget.isSpending
            self.amount = Double(budget.amount) / 100
            self.content = budget.content
        }
    }
}
