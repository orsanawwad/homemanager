package homemanager.app

class VeryGenericInventoryFactory: AbstractInventoryFactory() {
    override fun CreateInventory(): IInventory {
        return VeryGenericInventory()
    }
}