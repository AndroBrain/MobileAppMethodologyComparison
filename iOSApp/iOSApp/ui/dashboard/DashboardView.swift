//
//  DashboardView.swift
//  iOSApp
//
//  Created by Michal Ankiersztajn on 14/05/2025.
//

import SwiftUI

struct DashboardView : View {
    @EnvironmentObject var vm: DashboardViewModel
    
    var body: some View {
        NavigationSplitView {
            List(selection: $vm.selectedBudget) {
                ForEach(vm.budgets) { budget in
                    NavigationLink(value: budget) {
                        BudgetCardView(budget: budget)
                    }
                }
                .onDelete(perform: {indexSet in
                    vm.deleteBudget(indexSet: indexSet)
                })
                
            }
            .toolbar {
                ToolbarItem(placement: .principal) {
                    Text(String(format: "%.2f zł", Double(vm.balance) / 100))
                }
                ToolbarItem(placement: .navigationBarTrailing) {
                    Button {
                        vm.createEmptyBudget()
                    } label: {
                        Image(systemName: "plus.app.fill")
                            .resizable()
                            .frame(width: 32, height: 32)
                    }
                }
            }
        } detail: {
            if vm.selectedBudget != nil {
                EditBudgetView(budget: vm.selectedBudget!)
            }
        }
    }
}
