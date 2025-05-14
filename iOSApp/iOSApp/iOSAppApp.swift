//
//  iOSAppApp.swift
//  iOSApp
//
//  Created by Michal Ankiersztajn on 14/05/2025.
//

import SwiftUI

@main
struct iOSAppApp: App {
    let coreDataManager = CoreDataManager()
    @StateObject var budgetViewModel: DashboardViewModel
    
    init () {
        let budgetDataSource: BudgetDataSource = CoreBudgetdataSource(manager: coreDataManager)
        let budgetRepository = BudgetRepositoryImpl(dataSource: budgetDataSource)
        let budgetViewModel = DashboardViewModel(repository: budgetRepository)
        _budgetViewModel = StateObject(wrappedValue: budgetViewModel)
    }
    
    var body: some Scene {
        WindowGroup {
            DashboardView()
                .environmentObject(budgetViewModel)
        }
    }
}
