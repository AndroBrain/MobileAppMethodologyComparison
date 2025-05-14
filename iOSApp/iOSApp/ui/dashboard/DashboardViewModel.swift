//
//  DashboardViewModel.swift
//  iOSApp
//
//  Created by Michal Ankiersztajn on 14/05/2025.
//

import Foundation

class DashboardViewModel : ObservableObject {
    
    let repository: BudgetRepository
    @Published var budgets: [BudgetModel] = []
    @Published var selectedBudget: BudgetModel? = nil
    @Published var balance: Int64 = 0
    
    init(repository: BudgetRepository) {
        self.repository = repository
        loadBudgets()
    }
    
    func createEmptyBudget() {
        let budget = BudgetModel(amount: 0, content: "", id: UUID(), isSpending: false)
        repository.addOrReplace(budget: budget)
        loadBudgets()
        selectedBudget = budget
    }
    
    func deleteBudget(indexSet: IndexSet) {
        indexSet.forEach { index in
            print(index)
            repository.delete(id: budgets[index].id)
        }
        loadBudgets()
    }
    
    func updateNote(budget: BudgetModel) {
        repository.addOrReplace(budget: budget)
        loadBudgets()
        selectedBudget = nil
    }
    
    private func loadBudgets() {
        budgets = repository.getAll().reversed()
        var balance: Int64 = 0
        budgets.forEach { budget in
            if budget.isSpending {
                balance -= budget.amount
            } else {
                balance += budget.amount
            }
        }
        self.balance = balance
        print(balance)
        print("self" + String(self.balance))
    }
}
