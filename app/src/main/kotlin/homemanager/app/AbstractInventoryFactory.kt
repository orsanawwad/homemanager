package homemanager.app

abstract class AbstractInventoryFactory {
    abstract fun CreateInventory(): IInventory
}