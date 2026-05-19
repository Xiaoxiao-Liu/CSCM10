from dataclasses import dataclass, field


@dataclass
class Item:
    token: str = ""
    frequency: int = 0
    tfidf: int = 0
    value: int = 0
    color: dict = field(default_factory=lambda: {"r": 0, "g": 0, "b": 0, "a": 1.0})
    translations: list = field(default_factory=list)

    def to_dict(self):
        d = {
            "token": self.token,
            "frequency": self.frequency,
            "value": self.value,
            "color": self.color,
        }
        if self.translations:
            d["translations"] = self.translations
        return d


@dataclass
class Version:
    name: str = ""
    author: str = ""
    year: int = 0
    is_base: bool = False
    items: list = field(default_factory=list)

    def to_dict(self):
        return {
            "name": self.name,
            "author": self.author,
            "year": self.year,
            "is_base": self.is_base,
            "items": [item.to_dict() for item in self.items],
        }
